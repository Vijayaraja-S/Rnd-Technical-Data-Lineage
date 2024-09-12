package com.p3.poc.parser.parsing.handler.expression.service_impl.offset;

import com.p3.poc.parser.bean.parsing_details.OffsetInfo;
import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import io.trino.sql.tree.*;

public class OffsetProcessor extends AbstractExpressionProcessor {
    private OffsetInfo offsetInfo;

    public OffsetProcessor(OffsetInfo offsetInfo) {
        this.offsetInfo = offsetInfo;
    }

    @Override
    public void processDereference(DereferenceExpression dereferenceExpression) {
        //
    }

    @Override
    public void processComparison(ComparisonExpression comparisonExpression) {
        //
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
    public void processFunctionCall(FunctionCall functionCall) {
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
