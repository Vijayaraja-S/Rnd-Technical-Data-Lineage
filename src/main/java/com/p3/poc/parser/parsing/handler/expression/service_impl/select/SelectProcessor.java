package com.p3.poc.parser.parsing.handler.expression.service_impl.select;

import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import io.trino.sql.tree.*;

public class SelectProcessor extends AbstractExpressionProcessor {

    private final SelectHelper selectHelper;

    public SelectProcessor() {
        this.selectHelper = new SelectHelper();
    }

    @Override
    public Object processDereference(DereferenceExpression dereferenceExpression) {
        return null;
    }

    @Override
    public Object processComparison(ComparisonExpression comparisonExpression) {
        return null;
    }

    @Override
    public Object processFunctionCall(FunctionCall functionCall) {
        return null;
    }

    @Override
    public Object processLongLiteral(LongLiteral longLiteral) {
        return null;
    }

    @Override
    public Object processLogical(LogicalExpression logicalExp) {
        return null;
    }

    @Override
    public Object processIdentifier(Identifier identifier) {
        return null;
    }

    @Override
    public Object processBetween(BetweenPredicate betweenPredicate) {
        return null;
    }

    @Override
    public Object processIsNotNull(IsNotNullPredicate isNotNullPredicate) {
        return null;
    }
}
