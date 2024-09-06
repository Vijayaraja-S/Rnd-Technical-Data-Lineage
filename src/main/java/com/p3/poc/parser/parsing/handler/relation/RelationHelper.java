package com.p3.poc.parser.parsing.handler.relation;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.lineage.bean.flow.db_objs.TableDetails;
import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.relation.BaseRelationInfo;
import com.p3.poc.parser.bean.relation.sub_relation.join_criteria.JoinCriteriaInfo;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class RelationHelper {
    public JoinCriteriaInfo handleJoinCriteria(JoinCriteria joinCriteria) {
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
    public BaseRelationInfo getNestedRelationInfo(Relation relation, TableDetails tableDetails) {
        return new RelationHandler().handleRelation(relation, tableDetails);
    }

    public BaseExpressionInfo getNestedExpressionInfo(Expression expression) {
        return new ExpressionHandler().handleExpression(expression, ColumnDetails.builder().build());
    }
}
