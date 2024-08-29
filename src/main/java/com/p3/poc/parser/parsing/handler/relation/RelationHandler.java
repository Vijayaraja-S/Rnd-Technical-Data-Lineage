package com.p3.poc.parser.parsing.handler.relation;

import com.p3.poc.parser.bean.relation.BaseRelationInfo;
import com.p3.poc.parser.bean.relation.sub_relation.AliasedRelationInfo;
import com.p3.poc.parser.bean.relation.sub_relation.JoinRelationInfo;
import com.p3.poc.parser.bean.relation.sub_relation.TableRelationInfo;
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
public class RelationHandler {
    private final RelationProcessor relationProcessor;

    private final Map<Class<? extends Relation>, Function<Relation, ? extends BaseRelationInfo>> handlers;

    @SneakyThrows
    public RelationHandler() {
        this.relationProcessor = new RelationProcessor();
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
        return relationProcessor.processJoin(joinRelation);
    }

    private AliasedRelationInfo aliasedRelationHandler(Relation relation) {
        final AliasedRelation aliasedRelation = (AliasedRelation) relation;
        return relationProcessor.processAlias(aliasedRelation);
    }

    private TableRelationInfo tableRelationHandler(Relation relation) {
        final Table table = (Table) relation;
        return relationProcessor.processTable(table);
    }

    private BaseRelationInfo handleUnknownExpression(Relation relation) {
        return null;
    }


}

