package com.p3.poc.parser.parsing.handler.expression.service_impl.order_by;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.lineage.bean.flow.db_objs.OrderByInfo;
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
    public Object processDereference(DereferenceExpression dereferenceExpression) {
        final ColumnDetails columnDetails = expressionUtils.getColumnDetails(dereferenceExpression);
        final ColumnDetails col = expressionUtils.saveColumnDetails(columnDetails, NodeType.ORDER);
        orderByInfo.setColumnId(col.getColumnId());
        orderByInfo.setColumnName(col.getColumnName());
        orderByHelper.addOrderByDetails(orderByInfo);

        return null;
    }

    @Override
    public Object processComparison(ComparisonExpression comparisonExpression) {
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
    public Object processFunctionCall(FunctionCall functionCall) {
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
