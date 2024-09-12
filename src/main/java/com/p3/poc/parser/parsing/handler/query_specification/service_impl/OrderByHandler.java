package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.parser.bean.parsing_details.OrderByInfo;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import io.trino.sql.tree.*;

import java.util.List;

public class OrderByHandler extends AbstractQuerySpecHandler {
    private final ExpressionHandler expressionHandler;
    private final OrderBy orderBy;

    public OrderByHandler(OrderBy node) {
        this.orderBy = node;
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public void process() {
        final List<SortItem> sortItems = orderBy.getSortItems();
        sortItems.forEach(sortItem -> {
            final OrderByInfo orderByInfo = OrderByInfo.builder()
                    .orderType(sortItem.getOrdering().toString())
                    .build();
            final Expression sortKey = sortItem.getSortKey();
            expressionHandler.handleExpression(sortKey, NodeType.ORDER, orderByInfo);
        });
    }
}
