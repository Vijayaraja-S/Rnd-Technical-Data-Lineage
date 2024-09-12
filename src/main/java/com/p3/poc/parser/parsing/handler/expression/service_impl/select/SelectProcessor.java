package com.p3.poc.parser.parsing.handler.expression.service_impl.select;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import com.p3.poc.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import com.p3.poc.parser.parsing.handler.utils.QuerySpecificationChecker;
import io.trino.sql.tree.*;

import java.util.List;

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
        List<Node> children = functionCall.getChildren();
        children.forEach(child -> {
            AbstractQuerySpecHandler check = QuerySpecificationChecker.check(child);
            if (check != null) {
                check.process();
            }
        });
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
