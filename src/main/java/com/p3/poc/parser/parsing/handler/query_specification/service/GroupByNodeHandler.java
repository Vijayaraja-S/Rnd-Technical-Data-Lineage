package com.p3.poc.parser.parsing.handler.query_specification.service;

import io.trino.sql.tree.GroupBy;

public interface GroupByNodeHandler {
    void processGroupNode(GroupBy groupBy);
}
