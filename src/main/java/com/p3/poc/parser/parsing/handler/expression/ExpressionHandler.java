package com.p3.poc.parser.parsing.handler.expression;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.function.Function;

@Slf4j
public class ExpressionHandler {
    private ExpressionProcessor expressionProcessor;
    private final Map<Class<? extends Expression>, Function<Expression,Object>> handlers;

    public ExpressionHandler() {
        this.handlers = Map.of(
                Identifier.class,this::handleIdentifierExpression,
                DereferenceExpression.class, this::handleDereferenceExpression,
                ComparisonExpression.class,this::handleComparisonExpression,
                LongLiteral.class,this::handleLongLiteralExp,
                LogicalExpression.class,this::handleLogicalExp,
                IsNotNullPredicate.class,this::handleNotNullPrediction,
                BetweenPredicate.class,this::handleBetweenPrediction,
                FunctionCall.class,this::handleFunctionCallExp
        );
    }

    public Object handleExpression(Expression expression, ExpressionType expressionType,Object commonBean) {
        this.expressionProcessor = new ExpressionProcessor(expressionType,commonBean);
        final Function<Expression, Object> handler = handlers.getOrDefault(expression.getClass(), this::handleUnknownExpression);
        return handler.apply(expression);
    }

    private Object handleFunctionCallExp(Expression expression) {
        final FunctionCall functionCall = (FunctionCall) expression;
        return expressionProcessor.processExpression(functionCall);
    }

    private Object handleBetweenPrediction(Expression expression) {
        final BetweenPredicate betweenPredicate = (BetweenPredicate) expression;
        return expressionProcessor.processExpression(betweenPredicate);
    }

    private Object handleNotNullPrediction(Expression expression) {
        final IsNotNullPredicate isNotNullPredicate = (IsNotNullPredicate) expression;
        return expressionProcessor.processExpression(isNotNullPredicate);
    }

    private Object handleIdentifierExpression(Expression expression) {
        final Identifier identifier = (Identifier) expression;
        return expressionProcessor.processExpression(identifier);
    }

    private Object handleLogicalExp(Expression expression) {
        final LogicalExpression logicalExp = (LogicalExpression) expression;
        return expressionProcessor.processExpression(logicalExp);
    }

    private Object handleLongLiteralExp(Expression expression) {
        final LongLiteral longLiteral = (LongLiteral) expression;
        return expressionProcessor.processExpression(longLiteral);
    }

    private Object handleComparisonExpression(Expression expression) {
        final ComparisonExpression comparisonExpression = (ComparisonExpression) expression;
        return expressionProcessor.processExpression(comparisonExpression);
    }

    private Object handleDereferenceExpression(Expression expression) {
        DereferenceExpression dereferenceExpression = (DereferenceExpression) expression;
        return expressionProcessor.processExpression(dereferenceExpression);
    }

    public void saveColumnDetails(ColumnDetails columnDetails, ExpressionType type) {
        expressionProcessor.saveColumnDetails(columnDetails,type);
    }

    private Object handleUnknownExpression(Expression expression){
        log.error("Unknown expression: {}", expression);
        return ColumnDetails.builder().build();
    }

}

