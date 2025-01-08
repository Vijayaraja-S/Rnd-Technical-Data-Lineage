package com.p3.poc.lineage.parser.parsing.handler.query.service;

import io.trino.sql.tree.Node;
import io.trino.sql.tree.QueryBody;

public interface OtherQueryHandler {
    void handleQueryBody(QueryBody queryBody);
    void handleOrderBy(Node node);
    void handleOffset(Node node);
    void handleLimit(Node node);
}
