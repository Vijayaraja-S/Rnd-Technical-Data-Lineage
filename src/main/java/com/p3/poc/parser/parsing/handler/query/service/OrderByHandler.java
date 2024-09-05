package com.p3.poc.parser.parsing.handler.query.service;

import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import io.trino.sql.tree.Node;

public interface OrderByHandler {
    OrderByInfo handleOrderBy(Node node);
}
