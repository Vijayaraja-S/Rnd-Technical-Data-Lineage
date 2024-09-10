package com.p3.poc.parser.parsing.handler.expression.service;

import io.trino.sql.tree.LogicalExpression;

public interface Logical {
    void processLogicalExpression(LogicalExpression logicalExpression);
}
