package com.p3.poc.parser.parsing.handler.query.service;

import com.p3.poc.parser.bean.query.query_body.BaseQueryBodyInfo;
import io.trino.sql.tree.QueryBody;

public interface QueryBodyHandler {
    BaseQueryBodyInfo handleQueryBody(QueryBody queryBody);
}
