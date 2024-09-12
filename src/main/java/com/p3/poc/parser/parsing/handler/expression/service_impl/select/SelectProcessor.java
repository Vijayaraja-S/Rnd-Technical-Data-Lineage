package com.p3.poc.parser.parsing.handler.expression.service_impl.select;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import io.trino.sql.tree.*;

public class SelectProcessor extends AbstractExpressionProcessor {

    private final SelectHelper selectHelper;
    private final ColumnDetails columnDetails;

    public SelectProcessor(ColumnDetails columnDetails) {
        this.columnDetails = columnDetails;
        this.selectHelper = new SelectHelper();
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
