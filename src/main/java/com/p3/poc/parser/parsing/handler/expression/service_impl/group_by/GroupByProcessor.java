package com.p3.poc.parser.parsing.handler.expression.service_impl.group_by;

import com.p3.poc.parser.bean.parsing_details.GroupInfo;
import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import io.trino.sql.tree.*;

public class GroupByProcessor extends AbstractExpressionProcessor {
    private GroupInfo groupInfo;

    public GroupByProcessor(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }

    @Override
    public void processDereference(DereferenceExpression dereferenceExpression) {

    }

    @Override
    public void processComparison(ComparisonExpression comparisonExpression) {
    }

    @Override
    public void processFunctionCall(FunctionCall functionCall) {

    }

    @Override
    public void processLongLiteral(LongLiteral longLiteral) {

    }

    @Override
    public void processLogical(LogicalExpression logicalExp) {

    }

    @Override
    public void processIdentifier(Identifier identifier) {

    }

    @Override
    public void processBetween(BetweenPredicate betweenPredicate) {

    }

    @Override
    public void processIsNotNull(IsNotNullPredicate isNotNullPredicate) {
    }
}
