package com.p3.poc.parser.parsing.handler.expression.service;

import io.trino.sql.tree.FunctionCall;

public interface FunctionCallExpression {
    void processFunctionExpression(FunctionCall functionCall);
}
