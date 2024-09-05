package com.p3.poc.parser.parsing.handler.query.service_impl;

import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import com.p3.poc.parser.parsing.handler.query.service.OffsetHandler;
import io.trino.sql.tree.Node;

public class OffsetHandlerImpl implements OffsetHandler {
    @Override
    public OffsetInfo handleOffset(Node node) {
        // Logic to handle the OFFSET clause
        return new OffsetInfo();
    }
}


