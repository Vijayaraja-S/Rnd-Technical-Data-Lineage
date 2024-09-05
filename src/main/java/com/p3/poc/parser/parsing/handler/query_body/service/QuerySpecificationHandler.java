package com.p3.poc.parser.parsing.handler.query_body.service;

import com.p3.poc.parser.bean.query.query_body.QuerySpecificationDetails;
import io.trino.sql.tree.QuerySpecification;

public interface QuerySpecificationHandler {
    QuerySpecificationDetails handleQuerySpecification(QuerySpecification querySpec);
}
