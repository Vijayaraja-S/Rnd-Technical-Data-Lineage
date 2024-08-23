package com.p3.poc.parser.parsing.handler.relation;

import com.p3.poc.parser.bean.from_relation.BaseRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_relation.AliasedRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_relation.JoinRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_relation.TableRelationInfo;
import io.trino.sql.tree.*;
import lombok.Data;

import java.util.Optional;

@Data
public class IndividualRelationProcessor {

    public AliasedRelationInfo processRelation(AliasedRelationInfo relationInfo, AliasedRelation aliasedRelation) {
        final Relation relation = aliasedRelation.getRelation();
        final BaseRelationInfo baseRelationInfoBean = getNestedRelationInfo(relation);
        relationInfo.setRelationInfoDetails(baseRelationInfoBean);
        relationInfo.setAliasName(String.valueOf(aliasedRelation.getAlias()));
        return relationInfo;
    }

    public JoinRelationInfo processRelation(JoinRelationInfo relationInfo, Join joinRelation) {
        return relationInfo;
    }

    public TableRelationInfo processRelation(TableRelationInfo relationInfo, Table tableRelation) {
        final QualifiedName name = tableRelation.getName();
        final Optional<QualifiedName> schemaName = name.getPrefix();
        schemaName.ifPresent(qualifiedName -> relationInfo.setSchemaName(String.valueOf(qualifiedName)));
        relationInfo.setTableName(String.valueOf(name.getSuffix()));
        relationInfo.setFullTableName(name.toString());
        return relationInfo;
    }


    private BaseRelationInfo getNestedRelationInfo(Relation relation) {
        return new CommonRelationHandler().handleExpression(relation);
    }
}
