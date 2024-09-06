package com.p3.poc.parser.parsing.handler.expression;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.expression.sub_expression.*;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class ExpressionHandler {
    private ExpressionProcessor expressionProcessor;


    private final Map<Class<? extends Expression>, Function<Expression, ? extends BaseExpressionInfo>> handlers;


    public ExpressionHandler() {
        this.handlers = Map.of(
                DereferenceExpression.class, this::handleDereferenceExpression,
                FunctionCall.class, this::handleFunctionCall,
                ComparisonExpression.class,this::handleComparisonExpression,
                LongLiteral.class,this::handleLongLiteralExp,
                LogicalExpression.class,this::handleLogicalExp
        );
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseExpressionInfo> T handleExpression(Expression expression, ColumnDetails column) {
        this.expressionProcessor = new ExpressionProcessor(column);

        final Function<Expression, ? extends BaseExpressionInfo> handler = handlers.
                getOrDefault(expression.getClass(), this::handleUnknownExpression);
        return (T) handler.apply(expression);
    }

    private LogicalExpInfo handleLogicalExp(Expression expression) {
        final LogicalExpression logicalExp = (LogicalExpression) expression;
        final LogicalExpInfo bean = LogicalExpInfo.getBean();
        bean.setOperator(logicalExp.getOperator());
        final ArrayList<BaseExpressionInfo> exps = new ArrayList<>();
        for (Expression term : logicalExp.getTerms()) {
            exps.add(handleExpression(term, ColumnDetails.builder().build()));
        }
        bean.setBaseExpressionInfoList(exps);
        return bean;
    }

    private LongLiteralExpInfo handleLongLiteralExp(Expression expression) {
        final LongLiteral longLiteral = (LongLiteral) expression;
        final LongLiteralExpInfo bean = LongLiteralExpInfo.getBean();
        bean.setValue(longLiteral.getValue());
        return bean;
    }

    private ComparisonExpInfo handleComparisonExpression(Expression expression) {
        final ComparisonExpression comparisonExpression = (ComparisonExpression) expression;
        return expressionProcessor.processExpression(ComparisonExpInfo.getBean(), comparisonExpression);
    }

    private DeReferenceExpInfo handleDereferenceExpression(Expression expression) {
        DereferenceExpression dereferenceExpression = (DereferenceExpression) expression;
        return expressionProcessor.processExpression(DeReferenceExpInfo.getBean(), dereferenceExpression);
    }

    private FunctionCallExpInfo handleFunctionCall(Expression expression) {
        FunctionCall functionCall = (FunctionCall) expression;
        return expressionProcessor.processExpression(FunctionCallExpInfo.getBean(), functionCall);
    }


    private BaseExpressionInfo handleUnknownExpression(Expression expression) {
        return null;
    }

}

