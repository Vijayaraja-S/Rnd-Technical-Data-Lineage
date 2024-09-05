package com.p3.poc.parser.parsing.handler.query.service;

import com.p3.poc.parser.bean.query.query_body.BaseQueryBodyInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import io.trino.sql.tree.Node;
import io.trino.sql.tree.QueryBody;

public interface OtherQueryHandler {
    BaseQueryBodyInfo handleQueryBody(QueryBody queryBody);
    OrderByInfo handleOrderBy(Node node);
    OffsetInfo handleOffset(Node node);
    LimitInfo handleLimit(Node node);
}
