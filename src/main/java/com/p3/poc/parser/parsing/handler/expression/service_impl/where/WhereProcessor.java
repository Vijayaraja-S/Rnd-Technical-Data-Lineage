package com.p3.poc.parser.parsing.handler.expression.service_impl.where;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.OperationType;
import com.p3.poc.parser.parsing.handler.expression.bean.WhereExpressionInfo;
import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.utils.ExpressionUtils;
import io.trino.sql.tree.*;

import java.util.List;

public class WhereProcessor extends AbstractExpressionProcessor {
    private final WhereExpressionInfo whereExpressionInfo;

    private final ExpressionHandler expressionHandler;
    private final ExpressionUtils expressionUtils;
    private final WhereHelper whereHelper;

    public WhereProcessor(WhereExpressionInfo whereExpressionInfo) {
        this.whereExpressionInfo = whereExpressionInfo;
        this.expressionHandler = new ExpressionHandler();
        this.expressionUtils = new ExpressionUtils();
        this.whereHelper = new WhereHelper();
    }




    @Override
    public Object processDereference(DereferenceExpression dereferenceExpression) {
        final ColumnDetails columnDetails = expressionUtils.getColumnDetails(dereferenceExpression);
        final ColumnDetails col = expressionUtils.saveColumnDetails(columnDetails, NodeType.WHERE);
        whereExpressionInfo.setColumnId(col.getColumnId());
        whereExpressionInfo.setColumnName(col.getColumnName());
        return null;
    }

    @Override
    public Object processComparison(ComparisonExpression comparisonExpression) {
        whereExpressionInfo.setConditionType(OperationType.COMPARISON);
        whereExpressionInfo.setOperator(comparisonExpression.getOperator().toString());
        whereExpressionInfo.setExpressionContent(comparisonExpression.toString());
        whereHelper.processChildExpressions(comparisonExpression);
        whereHelper.addWhereDetails(whereExpressionInfo);
        return null;
    }

    @Override
    public Object processLongLiteral(LongLiteral longLiteral) {
        return null;
    }

    @Override
    public Object processLogical(LogicalExpression logicalExpression) {
        final List<? extends Node> children = logicalExpression.getChildren();
        children.forEach(
                child -> {
                    if (child instanceof Expression expression) {
                        whereHelper.processNestedExpression(null, expression);
                    }
                }
        );
        return null;
    }

    @Override
    public Object processIsNotNull(IsNotNullPredicate isNotNullPredicate) {
        whereExpressionInfo.setConditionType(OperationType.NOT_NULL);
        whereHelper.processNestedExpression(whereExpressionInfo, isNotNullPredicate.getValue());

        whereHelper.addWhereDetails(whereExpressionInfo);
        return null;
    }

    @Override
    public Object processBetween(BetweenPredicate betweenPredicate) {
        whereExpressionInfo.setConditionType(OperationType.BETWEEN);
        whereExpressionInfo.setMax(String.valueOf(betweenPredicate.getMax()));
        whereExpressionInfo.setMin(String.valueOf(betweenPredicate.getMin()));
        whereHelper.processNestedExpression(whereExpressionInfo, betweenPredicate.getValue());

        whereHelper.addWhereDetails(whereExpressionInfo);
        return null;
    }

    @Override
    public Object processFunctionCall(FunctionCall functionCall) {
        return null;
    }

    @Override
    public Object processIdentifier(Identifier identifier) {
        return null;
    }
}
