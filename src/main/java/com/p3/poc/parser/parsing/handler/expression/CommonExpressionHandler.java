package com.p3.poc.parser.parsing.handler.expression;

import com.p3.poc.parser.bean.expression.sub_expression.*;
import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

@Slf4j
public class CommonExpressionHandler {
    private final IndividualExpressionProcessor expressionHandler;

    private final Map<Class<? extends Expression>, Function<Expression, ? extends BaseExpressionInfo>> handlers;


    public CommonExpressionHandler() {
        this.expressionHandler = new IndividualExpressionProcessor();
        this.handlers = Map.of(
                DereferenceExpression.class, this::handleDereferenceExpression,
                FunctionCall.class, this::handleFunctionCall,
                ComparisonExpression.class,this::handleComparisonExpression,
                LongLiteral.class,this::handleLongLiteralExp,
                LogicalExpression.class,this::handleLogicalExp
        );
    }

    private LogicalExpInfo handleLogicalExp(Expression expression) {
        final LogicalExpression logicalExp = (LogicalExpression) expression;
        final LogicalExpInfo bean = LogicalExpInfo.getBean();
        bean.setOperator(logicalExp.getOperator());
        final ArrayList<BaseExpressionInfo> exps = new ArrayList<>();
        for (Expression term : logicalExp.getTerms()) {
            exps.add(handleExpression(term));
        }
        bean.setBaseExpressionInfoList(exps);
        return bean;
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseExpressionInfo> T handleExpression(Expression expression) {
        final Function<Expression, ? extends BaseExpressionInfo> handler = handlers.
                getOrDefault(expression.getClass(), this::handleUnknownExpression);
        return (T) handler.apply(expression);
    }

    private LongLiteralExpInfo handleLongLiteralExp(Expression expression) {
        final LongLiteral longLiteral = (LongLiteral) expression;
        final LongLiteralExpInfo bean = LongLiteralExpInfo.getBean();
        bean.setValue(longLiteral.getValue());
        return bean;
    }

    private ComparisonExpInfo handleComparisonExpression(Expression expression) {
        final ComparisonExpression comparisonExpression = (ComparisonExpression) expression;
        return expressionHandler.processExpression(ComparisonExpInfo.getBean(), comparisonExpression);
    }

    private DeReferenceExpInfo handleDereferenceExpression(Expression expression) {
        DereferenceExpression dereferenceExpression = (DereferenceExpression) expression;
        return expressionHandler.processExpression(DeReferenceExpInfo.getBean(), dereferenceExpression);
    }

    private FunctionCallExpInfo handleFunctionCall(Expression expression) {
        FunctionCall functionCall = (FunctionCall) expression;
        return expressionHandler.processExpression(FunctionCallExpInfo.getBean(), functionCall);
    }


    private BaseExpressionInfo handleUnknownExpression(Expression expression) {
        return null;
    }

}

