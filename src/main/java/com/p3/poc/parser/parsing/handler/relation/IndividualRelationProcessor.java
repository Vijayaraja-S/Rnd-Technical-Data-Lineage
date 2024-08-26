package com.p3.poc.parser.parsing.handler.relation;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.from_relation.BaseRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_relation.AliasedRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_relation.JoinRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_relation.TableRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_relation.join_criteria.JoinCriteriaInfo;
import com.p3.poc.parser.parsing.handler.expression.CommonExpressionHandler;
import io.trino.sql.tree.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Data
@Slf4j
public class IndividualRelationProcessor {

    public AliasedRelationInfo processRelation(AliasedRelationInfo relationInfo, AliasedRelation aliasedRelation) {
        final Relation relation = aliasedRelation.getRelation();
        final BaseRelationInfo baseRelationInfoBean = getNestedRelationInfo(relation);
        relationInfo.setRelationInfoDetails(baseRelationInfoBean);
        relationInfo.setAliasName(String.valueOf(aliasedRelation.getAlias()));
        return relationInfo;
    }

    public JoinRelationInfo processRelation(JoinRelationInfo relationInfo, Join join) {
        relationInfo.setType(String.valueOf(join.getType()));
        relationInfo.setLeft(getNestedRelationInfo(join.getLeft()));
        relationInfo.setRight(getNestedRelationInfo(join.getRight()));
        final Optional<JoinCriteria> criteria = join.getCriteria();
        criteria.ifPresent(joinCriteria -> relationInfo.setJoinCriteria(handleJoinCriteria(joinCriteria)));
        return relationInfo;
    }

    private JoinCriteriaInfo handleJoinCriteria(JoinCriteria joinCriteria) {
        final JoinCriteriaInfo criteriaInfo = JoinCriteriaInfo.builder().build();
        if (joinCriteria instanceof JoinOn join) {
            criteriaInfo.setLeftExpression(getNestedExpressionInfo(join.getExpression()));
        } else if (joinCriteria instanceof JoinUsing joinUsing) {
            final List<String> listOfColumns = joinUsing.getColumns()
                    .stream()
                    .map(Identifier::getValue)
                    .toList();
            criteriaInfo.setColumns(listOfColumns);
        } else {
            log.warn("Unknown join criteria type: {}", joinCriteria.getClass().getName());
        }
        return criteriaInfo;
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
        return new CommonRelationHandler().handleRelation(relation);
    }

    private BaseExpressionInfo getNestedExpressionInfo(Expression expression) {
        return new CommonExpressionHandler().handleExpression(expression);
    }
}
