package com.p3.poc.parser.parsing.handler.query.service_impl;

import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.parsing.handler.query.service.LimitHandler;
import io.trino.sql.tree.Node;

public class LimitHandlerImpl implements LimitHandler {
    @Override
    public LimitInfo handleLimit(Node node) {
        // Logic to handle the LIMIT clause
        return new LimitInfo(); // Populate with necessary data
    }
}
