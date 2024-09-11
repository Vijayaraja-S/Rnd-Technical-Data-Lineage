package com.p3.poc.parser.parsing.handler.expression.service_impl;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.lineage.bean.flow.db_objs.ConditionType;
import com.p3.poc.lineage.bean.flow.db_objs.WhereExpressionInfo;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHelper;
import com.p3.poc.parser.parsing.handler.expression.ExpressionType;
import com.p3.poc.parser.parsing.handler.expression.service.*;
import io.trino.sql.tree.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WhereProcessor extends ExpressionHelper implements Logical,
        Comparison, Dereference, IsNotNull, Between {
    private final ExpressionHandler expressionHandler;
    private final Object commonBean;

    public WhereProcessor(Object commonBean) {
        this.commonBean = commonBean;
        this.expressionHandler = new ExpressionHandler();
    }


    @Override
    public void processLogicalExpression(LogicalExpression logicalExpression) {
        final List<? extends Node> children = logicalExpression.getChildren();
        children.forEach(
                child -> {
                    if (child instanceof Expression expression) {
                        processNestedExpression(null, expression);
                    }
                }
        );
    }

    @Override
    public void processDereferenceExpression(DereferenceExpression dereferenceExpression) {
        if (commonBean instanceof WhereExpressionInfo expressionInfo) {
            final ColumnDetails columnDetails = getColumnDetails(dereferenceExpression);
            final ColumnDetails col = saveColumnDetails(columnDetails, ExpressionType.WHERE);
            expressionInfo.setColumnId(col.getColumnId());
            expressionInfo.setColumnName(col.getColumnName());
        }
    }

    @Override
    public Object processComparisonExpression(ComparisonExpression comparisonExpression) {
        WhereExpressionInfo whereExpression = extractWhereExpressionInfo(commonBean);
        whereExpression.setConditionType(ConditionType.COMPARISON);
        whereExpression.setOperator(comparisonExpression.getOperator().toString());
        whereExpression.setExpressionContent(comparisonExpression.toString());
        processChildExpressions(comparisonExpression, whereExpression);

        addWhereDetails(whereExpression);
        return null;
    }

    @Override
    public void processIsNotNullExpression(IsNotNullPredicate isNotNullPredicate) {
        WhereExpressionInfo whereExpression = extractWhereExpressionInfo(commonBean);
        whereExpression.setConditionType(ConditionType.NOT_NULL);
        processNestedExpression(whereExpression,isNotNullPredicate.getValue());

        addWhereDetails(whereExpression);
    }

    @Override
    public void processBetweenExpression(BetweenPredicate betweenPredicate) {
        WhereExpressionInfo whereExpression = extractWhereExpressionInfo(commonBean);
        whereExpression.setConditionType(ConditionType.BETWEEN);
        whereExpression.setMax(String.valueOf(betweenPredicate.getMax()));
        whereExpression.setMin(String.valueOf(betweenPredicate.getMin()));
        processNestedExpression(whereExpression,betweenPredicate.getValue());

        addWhereDetails(whereExpression);
    }

    private void processChildExpressions(Expression expression, WhereExpressionInfo whereExpression) {
        for (Node child : expression.getChildren()) {
            if (child instanceof Expression exp) {
                processNestedExpression(whereExpression, exp);
            }
        }
    }

    private void processNestedExpression(WhereExpressionInfo whereExpression, Expression exp) {
        expressionHandler.handleExpression(exp, ExpressionType.WHERE, whereExpression);
    }

    private WhereExpressionInfo extractWhereExpressionInfo(Object bean) {
        if (bean instanceof WhereExpressionInfo whereExpressionInfo) {
            return whereExpressionInfo;
        }
        return WhereExpressionInfo.builder().build();
    }

    private void addWhereDetails(WhereExpressionInfo whereExpression) {
        final GlobalCollector instance = GlobalCollector.getInstance();
        final String selectId = instance.getDynamicSelectId();
        final Map<String, List<WhereExpressionInfo>> whereInfoMap = instance.getWhereInfoMap();
        if (whereInfoMap.containsKey(selectId)) {
            whereInfoMap.get(selectId).add(whereExpression);
        } else {
            List<WhereExpressionInfo> whereInfoList = new ArrayList<>();
            whereInfoList.add(whereExpression);
            whereInfoMap.put(selectId, whereInfoList);
        }
    }
}
