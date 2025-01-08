package com.p3.poc.lineage.parser.parsing.handler.expression.service;

import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractExpressionProcessor {

    public abstract void processDereference(DereferenceExpression dereferenceExpression);

    public abstract void processComparison(ComparisonExpression comparisonExpression);

    public abstract void processLongLiteral(LongLiteral longLiteral);

    public abstract void processLogical(LogicalExpression logicalExp);

    public abstract void processIdentifier(Identifier identifier);

    public abstract void processFunctionCall(FunctionCall functionCall);

    public abstract void processBetween(BetweenPredicate betweenPredicate);

    public abstract void processIsNotNull(IsNotNullPredicate isNotNullPredicate);

}
