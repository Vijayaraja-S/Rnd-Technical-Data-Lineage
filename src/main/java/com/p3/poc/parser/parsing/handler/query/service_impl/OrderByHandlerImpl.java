package com.p3.poc.parser.parsing.handler.query.service_impl;

import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import com.p3.poc.parser.parsing.handler.query.service.OrderByHandler;
import io.trino.sql.tree.Node;

public class OrderByHandlerImpl implements OrderByHandler {

    @Override
    public OrderByInfo handleOrderBy(Node node) {
        return null;
    }
}
