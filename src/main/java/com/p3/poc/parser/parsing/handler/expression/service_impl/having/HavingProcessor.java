package com.p3.poc.parser.parsing.handler.expression.service_impl.having;

import com.p3.poc.parser.parsing.handler.expression.bean.HavingExpressionInfo;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.OperationType;
import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import io.trino.sql.tree.*;

import java.util.List;

public class HavingProcessor extends AbstractExpressionProcessor {
    private final HavingExpressionInfo havingExpressionInfo;

    private final ExpressionHandler expressionHandler;
    private final HavingHelper havingHelper;

    public HavingProcessor(HavingExpressionInfo havingExpressionInfo) {
        this.havingExpressionInfo = havingExpressionInfo;
        this.havingHelper = new HavingHelper();
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public void processDereference(DereferenceExpression dereferenceExpression) {
        //
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
        //
    }

    @Override
    public void processLogical(LogicalExpression logicalExp) {
        //
    }

    @Override
    public void processIdentifier(Identifier identifier) {
        //
    }

    @Override
    public void processBetween(BetweenPredicate betweenPredicate) {
        //
    }

    @Override
    public void processIsNotNull(IsNotNullPredicate isNotNullPredicate) {
        //
    }
}
