package com.p3.poc.parser.parsing.handler.expression.service;

import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractExpressionProcessor {

    public abstract Object processDereference(DereferenceExpression dereferenceExpression);

    public abstract Object processComparison(ComparisonExpression comparisonExpression);

    public abstract Object processLongLiteral(LongLiteral longLiteral);

    public abstract Object processLogical(LogicalExpression logicalExp);

    public abstract Object processIdentifier(Identifier identifier);

    public abstract Object processFunctionCall(FunctionCall functionCall);

    public abstract Object processBetween(BetweenPredicate betweenPredicate);

    public abstract Object processIsNotNull(IsNotNullPredicate isNotNullPredicate);

}
