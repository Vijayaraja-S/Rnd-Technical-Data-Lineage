package com.p3.poc.parser.parsing.handler.expression.service_impl.order_by;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.bean.parsing_details.OrderByInfo;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import com.p3.poc.parser.parsing.handler.expression.utils.ExpressionUtils;
import io.trino.sql.tree.*;

public class OrderByProcessor extends AbstractExpressionProcessor {
    private final OrderByInfo orderByInfo;
    private final ExpressionUtils expressionUtils;
    private final OrderByHelper orderByHelper;

    public OrderByProcessor(OrderByInfo commonBean) {
        this.orderByInfo = commonBean;
        this.expressionUtils = new ExpressionUtils(NodeType.ORDER);
        this.orderByHelper = new OrderByHelper();
    }

    @Override
    public void processDereference(DereferenceExpression dereferenceExpression) {
        final ColumnDetails columnDetails = ColumnDetails.builder().build();
        expressionUtils.processColumnDetails(dereferenceExpression,columnDetails);
        expressionUtils.saveColumnDetails(columnDetails);
        orderByInfo.setId(columnDetails.getId());
        orderByInfo.setColumnName(columnDetails.getColumnName());
        orderByHelper.addOrderByDetails(orderByInfo);

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
