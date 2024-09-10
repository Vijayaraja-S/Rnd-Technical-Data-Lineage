package com.p3.poc.parser.parsing.handler.expression.service;

import io.trino.sql.tree.ComparisonExpression;

public interface Comparison {
    Object processComparisonExpression(ComparisonExpression comparisonExpression);
}
