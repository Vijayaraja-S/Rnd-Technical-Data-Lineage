package com.p3.poc.parser.parsing.handler.expression.service;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.utils.ExpressionUtils;
import com.p3.poc.parser.parsing.handler.utils.ExpressionHandleChecker;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.function.Consumer;
@Slf4j
public class ExpressionHandler {
    private AbstractExpressionProcessor expressionProcessor;
    private final Map<Class<? extends Expression>, Consumer<Expression>> handlers;

    public ExpressionHandler() {
        this.handlers = Map.of(
                Identifier.class, this::handleIdentifierExpression,
                DereferenceExpression.class, this::handleDereferenceExpression,
                ComparisonExpression.class, this::handleComparisonExpression,
                LongLiteral.class, this::handleLongLiteralExp,
                LogicalExpression.class, this::handleLogicalExp,
                IsNotNullPredicate.class, this::handleNotNullPrediction,
                BetweenPredicate.class, this::handleBetweenPrediction,
                FunctionCall.class, this::handleFunctionCallExp
        );
    }

    public void handleExpression(Expression expression, NodeType type, Object commonBean) {
        this.expressionProcessor = ExpressionHandleChecker.checkExpression(type, commonBean);
        Consumer<Expression> handler = handlers.getOrDefault(expression.getClass(), this::handleUnknownExpression);
        handler.accept(expression);
    }

    private void handleFunctionCallExp(Expression expression) {
        final FunctionCall functionCall = (FunctionCall) expression;
        expressionProcessor.processFunctionCall(functionCall);
    }

    private void handleBetweenPrediction(Expression expression) {
        final BetweenPredicate betweenPredicate = (BetweenPredicate) expression;
        expressionProcessor.processBetween(betweenPredicate);
    }

    private void handleNotNullPrediction(Expression expression) {
        final IsNotNullPredicate isNotNullPredicate = (IsNotNullPredicate) expression;
        expressionProcessor.processIsNotNull(isNotNullPredicate);
    }

    private void handleIdentifierExpression(Expression expression) {
        final Identifier identifier = (Identifier) expression;
        expressionProcessor.processIdentifier(identifier);
    }

    private void handleLogicalExp(Expression expression) {
        final LogicalExpression logicalExp = (LogicalExpression) expression;
        expressionProcessor.processLogical(logicalExp);
    }

    private void handleLongLiteralExp(Expression expression) {
        final LongLiteral longLiteral = (LongLiteral) expression;
        expressionProcessor.processLongLiteral(longLiteral);
    }

    private void handleComparisonExpression(Expression expression) {
        final ComparisonExpression comparisonExpression = (ComparisonExpression) expression;
        expressionProcessor.processComparison(comparisonExpression);
    }

    private void handleDereferenceExpression(Expression expression) {
        DereferenceExpression dereferenceExpression = (DereferenceExpression) expression;
        expressionProcessor.processDereference(dereferenceExpression);
    }

    public void saveColumnDetails(ColumnDetails columnDetails, NodeType type) {
        new ExpressionUtils().saveColumnDetails(columnDetails, type);
    }

    private void handleUnknownExpression(Expression expression) {
        log.error("Unknown expression: {}", expression);
    }
}
