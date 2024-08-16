package com.p3.poc.parser.parsing.handler.query;

import io.trino.sql.tree.Node;

public interface QueryProcessor {
    void processQueryObject(Node node);
}
