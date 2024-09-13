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
        System.out.println("vfsdg");
    }

    @Override
    public void processComparison(ComparisonExpression comparisonExpression) {
        System.out.println("vfsdg");
    }

    @Override
    public void processLongLiteral(LongLiteral longLiteral) {
        offsetInfo.setOffset(longLiteral.toString());
    }

    @Override
    public void processLogical(LogicalExpression logicalExp) {
        System.out.println("vfsdg");
    }

    @Override
    public void processIdentifier(Identifier identifier) {
        System.out.println("vfsdg");
    }

    @Override
    public void processFunctionCall(FunctionCall functionCall) {
        System.out.println("vfsdg");
    }

    @Override
    public void processBetween(BetweenPredicate betweenPredicate) {
        System.out.println("vfsdg");
    }

    @Override
    public void processIsNotNull(IsNotNullPredicate isNotNullPredicate) {
        System.out.println("vfsdg");
    }
}
