package com.p3.poc.parser.parsing.handler.expression.service_impl;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.lineage.bean.flow.db_objs.OrderByInfo;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHelper;
import com.p3.poc.parser.parsing.handler.expression.ExpressionType;
import com.p3.poc.parser.parsing.handler.expression.service.Dereference;
import io.trino.sql.tree.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderByProcessor extends ExpressionHelper implements Dereference {
    private final ExpressionHandler expressionHandler;
    private final Object commonBean;

    public OrderByProcessor(Object commonBean) {
        this.commonBean = commonBean;
        this.expressionHandler = new ExpressionHandler();
    }


    @Override
    public void processDereferenceExpression(DereferenceExpression dereferenceExpression) {
        if (commonBean instanceof OrderByInfo orderByInfo) {
            final ColumnDetails columnDetails = getColumnDetails(dereferenceExpression);
            final ColumnDetails col = saveColumnDetails(columnDetails, ExpressionType.ORDER);
            orderByInfo.setColumnId(col.getColumnId());
            orderByInfo.setColumnName(col.getColumnName());
            addOrderByDetails(orderByInfo);
        }
    }
    private void addOrderByDetails(OrderByInfo orderByInfo) {
        final GlobalCollector instance = GlobalCollector.getInstance();
        final String selectId = instance.getDynamicSelectId();
        final Map<String, List<OrderByInfo>> orderByInfoMap = GlobalCollector.getInstance().getOrderByInfoMap();
        if (orderByInfoMap.containsKey(selectId)) {
            orderByInfoMap.get(selectId).add(orderByInfo);
        } else {
            List<OrderByInfo> orderByInfos = new ArrayList<>();
            orderByInfos.add(orderByInfo);
            orderByInfoMap.put(selectId, orderByInfos);
        }
    }
}
