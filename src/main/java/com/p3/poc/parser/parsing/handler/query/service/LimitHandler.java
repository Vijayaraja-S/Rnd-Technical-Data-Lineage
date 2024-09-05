package com.p3.poc.parser.parsing.handler.query.service;

import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import io.trino.sql.tree.Node;

public interface LimitHandler {
    LimitInfo handleLimit(Node node);
}
