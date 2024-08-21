package com.p3.poc.parser.parsing.handler.expression;

import com.p3.poc.parser.bean.expression.ExpressionDetails;
import io.trino.sql.tree.DereferenceExpression;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.FunctionCall;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class CommonExpressionHandler {
    private final IndividualExpressionProcessor expressionHandler;

    private final Map<Class<? extends Expression>, Function<Expression, ExpressionDetails>> handlers;

    public CommonExpressionHandler() {
        this.expressionHandler = new IndividualExpressionProcessor();
        this.handlers = Map.of(
                DereferenceExpression.class, this::handleDereferenceExpression,
                FunctionCall.class, this::handleFunctionCall
        );
    }

    public List<ExpressionDetails> handleExpression(Expression expression) {
        List<ExpressionDetails> expressionResultList = new ArrayList<>();
        ExpressionDetails expressionResult = handlers.getOrDefault(expression.getClass(), this::handleUnknownExpression)
                .apply(expression);
        expressionResultList.add(expressionResult);
        return expressionResultList;
    }

    private ExpressionDetails handleDereferenceExpression(Expression expression) {
        DereferenceExpression dereferenceExpression = (DereferenceExpression) expression;
        return expressionHandler.processExpression(getExpressionInfoBean(), dereferenceExpression);
    }

    private ExpressionDetails handleFunctionCall(Expression expression) {
        FunctionCall functionCall = (FunctionCall) expression;
        return expressionHandler.processExpression(getExpressionInfoBean(), functionCall);
    }

    private ExpressionDetails handleUnknownExpression(Expression expression) {
        log.warn("Unsupported expression type: {}", expression.getClass());
        return getExpressionInfoBean();
    }

    private ExpressionDetails getExpressionInfoBean() {
        return ExpressionDetails.builder().build();
    }
}

