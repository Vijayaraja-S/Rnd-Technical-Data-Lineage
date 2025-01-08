package com.p3.poc.lineage.parser.parsing.handler.expression.service_impl.where;

import com.p3.poc.lineage.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.lineage.parser.parsing.handler.expression.bean.indentifier.OperationType;
import com.p3.poc.lineage.parser.parsing.handler.expression.bean.WhereExpressionInfo;
import com.p3.poc.lineage.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import com.p3.poc.lineage.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.lineage.parser.parsing.handler.expression.utils.ExpressionUtils;
import io.trino.sql.tree.*;

import java.util.List;

public class WhereProcessor extends AbstractExpressionProcessor {
    private final WhereExpressionInfo whereExpressionInfo;
    private final ExpressionUtils expressionUtils;
    private final WhereHelper whereHelper;

    public WhereProcessor(WhereExpressionInfo whereExpressionInfo) {
        this.whereExpressionInfo = whereExpressionInfo;
        this.expressionUtils = new ExpressionUtils(NodeType.WHERE);
        this.whereHelper = new WhereHelper();
    }


    @Override
    public void processDereference(DereferenceExpression dereferenceExpression) {
        final ColumnDetails columnDetails = ColumnDetails.builder().build();
        expressionUtils.processColumnDetails(dereferenceExpression, columnDetails);
        expressionUtils.saveColumnDetails(columnDetails);
        whereExpressionInfo.setColumnId(columnDetails.getId());
        whereExpressionInfo.setColumnName(columnDetails.getColumnName());
    }

    @Override
    public void processComparison(ComparisonExpression comparisonExpression) {
        whereExpressionInfo.setConditionType(OperationType.COMPARISON);
        whereExpressionInfo.setOperator(comparisonExpression.getOperator().toString());
        whereExpressionInfo.setExpressionContent(comparisonExpression.toString());
        whereHelper.processChildExpressions(comparisonExpression);
        whereHelper.addWhereDetails(whereExpressionInfo);
    }


    @Override
    public void processLogical(LogicalExpression logicalExpression) {
        final List<? extends Node> children = logicalExpression.getChildren();
        children.forEach(child -> {
            if (child instanceof Expression expression) {
                whereHelper.processNestedExpression(whereExpressionInfo, expression);
            }
        });
    }

    @Override
    public void processIsNotNull(IsNotNullPredicate isNotNullPredicate) {
        whereExpressionInfo.setConditionType(OperationType.NOT_NULL);
        whereHelper.processNestedExpression(whereExpressionInfo, isNotNullPredicate.getValue());

        whereHelper.addWhereDetails(whereExpressionInfo);
    }

    @Override
    public void processBetween(BetweenPredicate betweenPredicate) {
        whereExpressionInfo.setConditionType(OperationType.BETWEEN);
        whereExpressionInfo.setMax(String.valueOf(betweenPredicate.getMax()));
        whereExpressionInfo.setMin(String.valueOf(betweenPredicate.getMin()));
        whereHelper.processNestedExpression(whereExpressionInfo, betweenPredicate.getValue());

        whereHelper.addWhereDetails(whereExpressionInfo);
    }


    @Override
    public void processLongLiteral(LongLiteral longLiteral) {
        whereExpressionInfo.setRightValue(longLiteral.toString());
    }

    @Override
    public void processFunctionCall(FunctionCall functionCall) {
        System.out.println("vfsdg");
    }

    @Override
    public void processIdentifier(Identifier identifier) {
        System.out.println("vfsdg");
    }
}
