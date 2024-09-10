package com.p3.poc.parser.parsing.handler.expression.service;

import io.trino.sql.tree.IsNotNullPredicate;

public interface IsNotNull {
    void processIsNotNullExpression(IsNotNullPredicate isNotNullPredicate);
}
