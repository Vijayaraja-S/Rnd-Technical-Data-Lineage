package com.p3.poc.lineage.parser.parsing.handler.expression.service_impl.having;

import com.p3.poc.lineage.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.lineage.parser.parsing.handler.expression.bean.HavingExpressionInfo;
import com.p3.poc.lineage.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.lineage.parser.parsing.handler.expression.bean.indentifier.OperationType;
import com.p3.poc.lineage.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import com.p3.poc.lineage.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.lineage.parser.parsing.handler.expression.utils.ExpressionUtils;
import io.trino.sql.tree.*;

import java.util.List;

public class HavingProcessor extends AbstractExpressionProcessor {
    private final HavingExpressionInfo havingExpressionInfo;

    private final ExpressionHandler expressionHandler;
    private final HavingHelper havingHelper;
    private final ExpressionUtils expressionUtils;

    public HavingProcessor(HavingExpressionInfo havingExpressionInfo) {
        this.expressionUtils = new ExpressionUtils(NodeType.HAVING);
        this.havingExpressionInfo = havingExpressionInfo;
        this.havingHelper = new HavingHelper();
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public void processDereference(DereferenceExpression dereferenceExpression) {
        final ColumnDetails columnDetails = ColumnDetails.builder().build();
        expressionUtils.processColumnDetails(dereferenceExpression,columnDetails);
        expressionUtils.saveColumnDetails(columnDetails);
    }

    @Override
    public void processComparison(ComparisonExpression comparisonExpression) {
        final List<Node> children = comparisonExpression.getChildren();
        final ComparisonExpression.Operator operator = comparisonExpression.getOperator();
        havingExpressionInfo.setExpressionContent(comparisonExpression.toString());
        havingExpressionInfo.setConditionType(OperationType.COMPARISON);
        havingExpressionInfo.setOperator(operator.toString());
        children.forEach(
                child -> {
                    if (child instanceof Expression expression) {
                        HavingExpressionInfo havingExpression = new HavingExpressionInfo();
                        expressionHandler.handleExpression(expression, NodeType.HAVING, havingExpression);
                    }
                }
        );
        havingHelper.addHavingDetails(havingExpressionInfo);
    }

    @Override
    public void processFunctionCall(FunctionCall functionCall) {
        havingExpressionInfo.setMultiRowFunction(true);
        havingExpressionInfo.setLeft(String.valueOf(functionCall.getName()));

    }

    @Override
    public void processLongLiteral(LongLiteral longLiteral) {
        havingExpressionInfo.setRightValue(longLiteral.toString());
    }

    @Override
    public void processLogical(LogicalExpression logicalExp) {
        System.out.println("vfsdg");
    }

    @Override
    public void processIdentifier(Identifier identifier) {
        System.out.println("vfsdg");
    }

    @Override
    public void processBetween(BetweenPredicate betweenPredicate) {
        System.out.println("vfsdg");
    }

    @Override
    public void processIsNotNull(IsNotNullPredicate isNotNullPredicate) {
        System.out.println("vfsdg");
    }
}
