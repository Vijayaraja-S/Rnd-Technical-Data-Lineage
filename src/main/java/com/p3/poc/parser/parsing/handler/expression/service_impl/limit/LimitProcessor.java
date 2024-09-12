package com.p3.poc.parser.parsing.handler.expression.service_impl.limit;

import com.p3.poc.parser.bean.parsing_details.LimitInfo;
import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import io.trino.sql.tree.*;

public class LimitProcessor extends AbstractExpressionProcessor {
    private LimitInfo limitInfo;

    public LimitProcessor(LimitInfo limitInfo) {
        this.limitInfo = limitInfo;
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
