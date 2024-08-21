package com.p3.poc.parser.parsing.handler.relation;

import com.p3.poc.parser.bean.from_relation.BaseRelationInfoBean;
import com.p3.poc.parser.bean.from_relation.sub_bean.AliasedRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_bean.JoinRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_bean.TableRelationInfo;
import com.p3.poc.parser.parsing.exception.RelationHandler;
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

    private final Map<Class<? extends Relation>, Function<Relation, ? extends BaseRelationInfoBean>> handlers;

    @SneakyThrows
    public CommonRelationHandler() {
        this.relationHandler = new IndividualRelationProcessor();
        this.handlers = Map.of(
                AliasedRelation.class, this::aliasedRelationHandler,
                Join.class, this::joinRelationHandler,
                Table.class,this::tableRelationHandler
        );
    }

    public <T extends BaseRelationInfoBean> T handleExpression(Relation relation) {
        Function<Relation, ? extends BaseRelationInfoBean> handler =
                handlers.getOrDefault(relation.getClass(), this::handleUnknownExpression);

        return (T) handler.apply(relation);
    }

    private JoinRelationInfo joinRelationHandler(Relation relation) throws RelationHandler {
        final Join joinRelation = (Join) relation;
        if (joinRelation != null) {
         return  relationHandler.processRelation(JoinRelationInfo.getBean(), joinRelation);
        }else {
            throw new RelationHandler("Join relation is null");
        }
    }

    private AliasedRelationInfo aliasedRelationHandler(Relation relation) throws RelationHandler {
        final AliasedRelation aliasedRelation = (AliasedRelation) relation;
        if (aliasedRelation != null) {
            return relationHandler.processRelation( AliasedRelationInfo.getBean(), aliasedRelation);
        }else {
            throw new RelationHandler("aliasRelation relation is null");
        }
    }
    private TableRelationInfo tableRelationHandler(Relation relation) throws RelationHandler {
        final Table table = (Table) relation;
        if (table != null) {
         return relationHandler.processRelation(TableRelationInfo.getBean(), table);
        }else {
            throw new RelationHandler("table relation is null");
        }
    }


    private BaseRelationInfoBean handleUnknownExpression(Relation relation) {
        log.warn("Unsupported relation type: {}", relation.getClass());
        return BaseRelationInfoBean.getBean();
    }


}

