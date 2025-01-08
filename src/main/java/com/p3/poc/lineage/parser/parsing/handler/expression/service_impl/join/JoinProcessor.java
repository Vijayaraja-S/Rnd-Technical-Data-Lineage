package com.p3.poc.lineage.parser.parsing.handler.expression.service_impl.join;

import com.p3.poc.lineage.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.lineage.parser.bean.parsing_details.JoinDetailsInfo;
import com.p3.poc.lineage.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import com.p3.poc.lineage.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.lineage.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.lineage.parser.parsing.handler.expression.utils.ExpressionUtils;
import io.trino.sql.tree.*;

public class JoinProcessor extends AbstractExpressionProcessor {
    private final ExpressionHandler expressionHandler;
    private final JoinHelper joinHelper;
    private final JoinDetailsInfo joinDetailsInfo;
    private final ExpressionUtils expressionUtils;

    private ColumnDetails currentProcessColumn = new ColumnDetails();

    public JoinProcessor(JoinDetailsInfo joinDetailsInfo) {
        this.expressionUtils = new ExpressionUtils(NodeType.JOIN);
        this.joinDetailsInfo = joinDetailsInfo;
        this.expressionHandler = new ExpressionHandler();
        this.joinHelper = new JoinHelper();
    }


    @Override
    public void processDereference(DereferenceExpression dereferenceExpression) {
        if (currentProcessColumn != null) {
            expressionUtils.processColumnDetails(dereferenceExpression, currentProcessColumn);
            expressionUtils.saveColumnDetails(currentProcessColumn);
        }
    }

    @Override
    public void processComparison(ComparisonExpression comparisonExpression) {
        ColumnDetails left = ColumnDetails.builder().build();
        ColumnDetails right = ColumnDetails.builder().build();

        currentProcessColumn = right;
        expressionHandler.handleExpression(comparisonExpression.getRight(), NodeType.JOIN, joinDetailsInfo);

        currentProcessColumn = left;
        expressionHandler.handleExpression(comparisonExpression.getLeft(), NodeType.JOIN, joinDetailsInfo);

        final String joinId = right.getId() + "::" + left.getId();

        joinHelper.saveJoinDetailsInfo(comparisonExpression, joinId);
        joinHelper.setJoinProperties(left, joinId, joinDetailsInfo);
        joinHelper.setJoinProperties(right, joinId, joinDetailsInfo);
    }

    @Override
    public void processLongLiteral(LongLiteral longLiteral) {
        System.out.println("vfsdg");
    }

    @Override
    public void processLogical(LogicalExpression logicalExp) {
        System.out.println("vfsdg");
    }

    @Override
    public void processIdentifier(Identifier identifier) {
        System.out.println("vfsdg");
    }

    @Override
    public void processFunctionCall(FunctionCall functionCall) {
        System.out.println("vfsdg");
    }

    @Override
    public void processBetween(BetweenPredicate betweenPredicate) {
        System.out.println("vfsdg");
    }

    @Override
    public void processIsNotNull(IsNotNullPredicate isNotNullPredicate) {
        System.out.println("vfsdg");
    }
}
