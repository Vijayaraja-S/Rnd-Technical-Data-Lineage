package com.p3.poc.parser.parsing.handler.expression.service_impl.select;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.expression.utils.ExpressionUtils;
import com.p3.poc.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import com.p3.poc.parser.parsing.handler.utils.QuerySpecificationChecker;
import io.trino.sql.tree.*;

import java.util.List;

public class SelectProcessor extends AbstractExpressionProcessor {

    private final SelectHelper selectHelper;
    private final ColumnDetails columnDetails;
    private final ExpressionHandler expressionHandler;
    private final ExpressionUtils expressionUtils;

    public SelectProcessor(ColumnDetails columnDetails) {
        this.columnDetails = columnDetails;
        this.selectHelper = new SelectHelper();
        this.expressionHandler = new ExpressionHandler();
        this.expressionUtils = new ExpressionUtils(NodeType.SELECT);
    }

    @Override
    public void processDereference(DereferenceExpression dereferenceExpression) {
        expressionUtils.processColumnDetails(dereferenceExpression,columnDetails);
        expressionUtils.saveColumnDetails(columnDetails);
    }

    @Override
    public void processComparison(ComparisonExpression comparisonExpression) {
        System.out.println("vfsdg");
    }

    @Override
    public void processLongLiteral(LongLiteral longLiteral) {
        System.out.println("vfsdg");
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
        System.out.println("vfsdg");
    }

    @Override
    public void processIsNotNull(IsNotNullPredicate isNotNullPredicate) {
        System.out.println("vfsdg");
    }
}
