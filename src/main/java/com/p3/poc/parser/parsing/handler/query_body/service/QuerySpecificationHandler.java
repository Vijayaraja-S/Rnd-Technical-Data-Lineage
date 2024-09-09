package com.p3.poc.parser.parsing.handler.query_body.service;

import io.trino.sql.tree.QuerySpecification;

public interface QuerySpecificationHandler {
    void handleQuerySpecification(QuerySpecification querySpec);
}
