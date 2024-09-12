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
        this.expressionUtils = new ExpressionUtils();
        this.orderByHelper = new OrderByHelper();
    }

    @Override
    public void processDereference(DereferenceExpression dereferenceExpression) {
        final ColumnDetails columnDetails = expressionUtils.getColumnDetails(dereferenceExpression);
        final ColumnDetails col = expressionUtils.saveColumnDetails(columnDetails, NodeType.ORDER);
        orderByInfo.setId(col.getId());
        orderByInfo.setColumnName(col.getColumnName());
        orderByHelper.addOrderByDetails(orderByInfo);

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
