package com.p3.poc.parser.parsing.handler.query_specification.service;

import io.trino.sql.tree.Select;

public interface SelectNodeHandler {
    void processSelectNode(Select selectNode);
}
