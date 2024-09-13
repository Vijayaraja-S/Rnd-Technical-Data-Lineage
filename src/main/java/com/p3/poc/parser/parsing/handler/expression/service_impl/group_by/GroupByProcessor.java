package com.p3.poc.parser.parsing.handler.expression.service_impl.group_by;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.bean.parsing_details.GroupInfo;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.expression.utils.ExpressionUtils;
import io.trino.sql.tree.*;

public class GroupByProcessor extends AbstractExpressionProcessor {
    private GroupInfo groupInfo;
    private final ExpressionUtils expressionUtils;


    public GroupByProcessor(GroupInfo groupInfo) {
        this.expressionUtils = new ExpressionUtils(NodeType.GROUP_BY);
        this.groupInfo = groupInfo;
    }

    @Override
    public void processDereference(DereferenceExpression dereferenceExpression) {
        final ColumnDetails columnDetails = ColumnDetails.builder().build();
        expressionUtils.processColumnDetails(dereferenceExpression,columnDetails);
        expressionUtils.saveColumnDetails(columnDetails);
    }

    @Override
    public void processComparison(ComparisonExpression comparisonExpression) {
        System.out.println("vfsdg");
    }

    @Override
    public void processFunctionCall(FunctionCall functionCall) {
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
    public void processBetween(BetweenPredicate betweenPredicate) {
        System.out.println("vfsdg");
    }

    @Override
    public void processIsNotNull(IsNotNullPredicate isNotNullPredicate) {
        System.out.println("vfsdg");
    }
}
