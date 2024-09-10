package com.p3.poc.parser.parsing.handler.expression;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.lineage.bean.flow.db_objs.JoinDetailsInfo;
import com.p3.poc.parser.bean.GlobalCollector;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class ExpressionHandler {
    private ExpressionProcessor expressionProcessor;
    private final Map<Class<? extends Expression>, Function<Expression,ColumnDetails>> handlers;

    public ExpressionHandler() {
        this.handlers = Map.of(
                DereferenceExpression.class, this::handleDereferenceExpression,
                ComparisonExpression.class,this::handleComparisonExpression,
                LongLiteral.class,this::handleLongLiteralExp,
                LogicalExpression.class,this::handleLogicalExp
        );
    }

    public ColumnDetails handleExpression(Expression expression, boolean isJoin) {
        this.expressionProcessor = new ExpressionProcessor(isJoin);
        final Function<Expression, ColumnDetails> handler = handlers.getOrDefault(expression.getClass(), this::handleUnknownExpression);
        return handler.apply(expression);
    }

    private ColumnDetails handleLogicalExp(Expression expression) {
        final LogicalExpression logicalExp = (LogicalExpression) expression;
        return expressionProcessor.processExpression(logicalExp);
    }

    private ColumnDetails handleLongLiteralExp(Expression expression) {
        return null;
    }

    private ColumnDetails handleComparisonExpression(Expression expression) {
        final ComparisonExpression comparisonExpression = (ComparisonExpression) expression;
        return expressionProcessor.processExpression(comparisonExpression);
    }

    private ColumnDetails handleDereferenceExpression(Expression expression) {
        DereferenceExpression dereferenceExpression = (DereferenceExpression) expression;
        return expressionProcessor.processExpression(dereferenceExpression);
    }

    private ColumnDetails handleUnknownExpression(Expression expression) {
        log.error("Unknown expression: {}", expression);
        return ColumnDetails.builder().build();
    }

}

