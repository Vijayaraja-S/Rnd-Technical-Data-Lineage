package com.p3.poc.parser.parsing.handler.expression.service_impl.join;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.bean.parsing_details.JoinDetailsInfo;
import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import io.trino.sql.tree.*;

public class JoinProcessor extends AbstractExpressionProcessor {
    private final ExpressionHandler expressionHandler;
    private final JoinHelper joinHelper;
    private final JoinDetailsInfo joinDetailsInfo;

    public JoinProcessor(JoinDetailsInfo joinDetailsInfo) {
        this.joinDetailsInfo = joinDetailsInfo;
        this.expressionHandler = new ExpressionHandler();
        this.joinHelper = new JoinHelper();
    }


    @Override
    public void processDereference(DereferenceExpression dereferenceExpression) {
        //
    }

    @Override
    public void processComparison(ComparisonExpression comparisonExpression) {
        final ColumnDetails left = ColumnDetails.builder().build();
        final ColumnDetails right = ColumnDetails.builder().build();
        expressionHandler.handleExpression(comparisonExpression.getRight(), NodeType.JOIN, left);
        expressionHandler.handleExpression(comparisonExpression.getLeft(), NodeType.JOIN, right);
        expressionHandler.saveColumnDetails(left, NodeType.JOIN);
        expressionHandler.saveColumnDetails(right, NodeType.JOIN);
        final String joinId = right.getId() + "::" + left.getId();
        joinHelper.saveJoinDetailsInfo(comparisonExpression, joinId);
        joinHelper.setJoinProperties(left, joinId, joinDetailsInfo);
        joinHelper.setJoinProperties(right, joinId, joinDetailsInfo);
    }

    @Override
    public void processLongLiteral(LongLiteral longLiteral) {
        //
    }

    @Override
    public void processLogical(LogicalExpression logicalExp){
        //
    }

    @Override
    public void processIdentifier(Identifier identifier) {
        //
    }

    @Override
    public void processFunctionCall(FunctionCall functionCall){
        //
    }

    @Override
    public void processBetween(BetweenPredicate betweenPredicate) {
        //
    }

    @Override
    public void processIsNotNull(IsNotNullPredicate isNotNullPredicate) {
        //
    }
}
