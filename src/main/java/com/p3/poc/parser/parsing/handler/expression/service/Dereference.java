package com.p3.poc.parser.parsing.handler.expression.service;

import io.trino.sql.tree.DereferenceExpression;

public interface Dereference {
    void processDereferenceExpression(DereferenceExpression dereferenceExpression);
}
