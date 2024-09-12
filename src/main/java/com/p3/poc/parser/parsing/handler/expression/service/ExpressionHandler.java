package com.p3.poc.parser.parsing.handler.expression.service;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.utils.ExpressionUtils;
import com.p3.poc.parser.parsing.handler.utils.ExpressionHandleChecker;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.function.Function;

@Slf4j
public class ExpressionHandler {
    private AbstractExpressionProcessor expressionProcessor;
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

    public Object handleExpression(Expression expression, NodeType type, Object commonBean) {
        this.expressionProcessor = ExpressionHandleChecker.checkExpression(type, commonBean);
        final Function<Expression, Object> handler = handlers.getOrDefault(expression.getClass(), this::handleUnknownExpression);
        return handler.apply(expression);
    }

    private Object handleFunctionCallExp(Expression expression) {
        final FunctionCall functionCall = (FunctionCall) expression;
        return expressionProcessor.processFunctionCall(functionCall);
    }

    private Object handleBetweenPrediction(Expression expression) {
        final BetweenPredicate betweenPredicate = (BetweenPredicate) expression;
        return expressionProcessor.processBetween(betweenPredicate);
    }

    private Object handleNotNullPrediction(Expression expression) {
        final IsNotNullPredicate isNotNullPredicate = (IsNotNullPredicate) expression;
        return expressionProcessor.processIsNotNull(isNotNullPredicate);
    }

    private Object handleIdentifierExpression(Expression expression) {
        final Identifier identifier = (Identifier) expression;
        return expressionProcessor.processIdentifier(identifier);
    }

    private Object handleLogicalExp(Expression expression) {
        final LogicalExpression logicalExp = (LogicalExpression) expression;
        return expressionProcessor.processLogical(logicalExp);
    }

    private Object handleLongLiteralExp(Expression expression) {
        final LongLiteral longLiteral = (LongLiteral) expression;
        return expressionProcessor.processLongLiteral(longLiteral);
    }

    private Object handleComparisonExpression(Expression expression) {
        final ComparisonExpression comparisonExpression = (ComparisonExpression) expression;
        return expressionProcessor.processComparison(comparisonExpression);
    }

    private Object handleDereferenceExpression(Expression expression) {
        DereferenceExpression dereferenceExpression = (DereferenceExpression) expression;
        return expressionProcessor.processDereference(dereferenceExpression);
    }

    public void saveColumnDetails(ColumnDetails columnDetails, NodeType type) {
        new ExpressionUtils().saveColumnDetails(columnDetails,type);
    }

    private Object handleUnknownExpression(Expression expression){
        log.error("Unknown expression: {}", expression);
        return ColumnDetails.builder().build();
    }

}

