package com.p3.poc.parser.parsing.handler.relation;

import com.p3.poc.parser.bean.from_relation.BaseRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_relation.AliasedRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_relation.JoinRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_relation.TableRelationInfo;
import io.trino.sql.tree.AliasedRelation;
import io.trino.sql.tree.Join;
import io.trino.sql.tree.Relation;
import io.trino.sql.tree.Table;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.function.Function;

@Slf4j
@Data
public class CommonRelationHandler {
    private final IndividualRelationProcessor relationHandler;

    private final Map<Class<? extends Relation>, Function<Relation, ? extends BaseRelationInfo>> handlers;

    @SneakyThrows
    public CommonRelationHandler() {
        this.relationHandler = new IndividualRelationProcessor();
        this.handlers = Map.of(
                AliasedRelation.class, this::aliasedRelationHandler,
                Join.class, this::joinRelationHandler,
                Table.class, this::tableRelationHandler
        );
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseRelationInfo> T handleRelation(Relation relation) {
        Function<Relation, ? extends BaseRelationInfo> handler =
                handlers.getOrDefault(relation.getClass(), this::handleUnknownExpression);
        return (T) handler.apply(relation);
    }

    private JoinRelationInfo joinRelationHandler(Relation relation) {
        final Join joinRelation = (Join) relation;
        return relationHandler.processRelation(JoinRelationInfo.getBean(), joinRelation);
    }

    private AliasedRelationInfo aliasedRelationHandler(Relation relation) {
        final AliasedRelation aliasedRelation = (AliasedRelation) relation;
        return relationHandler.processRelation(AliasedRelationInfo.getBean(), aliasedRelation);
    }

    private TableRelationInfo tableRelationHandler(Relation relation) {
        final Table table = (Table) relation;
        return relationHandler.processRelation(TableRelationInfo.getBean(), table);
    }

    private BaseRelationInfo handleUnknownExpression(Relation relation) {
        return null;
    }


}

