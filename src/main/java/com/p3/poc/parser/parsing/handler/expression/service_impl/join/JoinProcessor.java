package com.p3.poc.parser.parsing.handler.expression.service_impl.join;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.lineage.bean.flow.db_objs.JoinDetailsInfo;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import io.trino.sql.tree.*;

import java.util.Map;

public class JoinProcessor extends AbstractExpressionProcessor {
    private final ExpressionHandler expressionHandler;
    private final JoinHelper joinHelper;

    public JoinProcessor() {
        this.expressionHandler = new ExpressionHandler();
        this.joinHelper = new JoinHelper();
    }


    @Override
    public Object processDereference(DereferenceExpression dereferenceExpression) {
        return null;
    }

    @Override
    public Object processComparison(ComparisonExpression comparisonExpression) {
        final Object obj1 = expressionHandler.handleExpression(comparisonExpression.getRight(), NodeType.JOIN, null);
        final Object obj2 = expressionHandler.handleExpression(comparisonExpression.getLeft(), NodeType.JOIN, null);
        if (obj1 instanceof ColumnDetails left && obj2 instanceof ColumnDetails right) {
            expressionHandler.saveColumnDetails(left, NodeType.JOIN);
            expressionHandler.saveColumnDetails(right, NodeType.JOIN);
            final String joinId = right.getColumnId() + "::" + left.getColumnId();
            final JoinDetailsInfo joinDetailsInfo = joinHelper.saveJoinDetailsInfo(comparisonExpression, joinId);
            joinHelper.setJoinProperties(left, joinId, joinDetailsInfo);
            joinHelper.setJoinProperties(right, joinId, joinDetailsInfo);
            return left;
        }
        return null;
    }

    @Override
    public Object processLongLiteral(LongLiteral longLiteral) {
        return null;
    }

    @Override
    public Object processLogical(LogicalExpression logicalExp) {
        return null;
    }

    @Override
    public Object processIdentifier(Identifier identifier) {
        return null;
    }

    @Override
    public Object processFunctionCall(FunctionCall functionCall) {
        return null;
    }

    @Override
    public Object processBetween(BetweenPredicate betweenPredicate) {
        return null;
    }

    @Override
    public Object processIsNotNull(IsNotNullPredicate isNotNullPredicate) {
        return null;
    }
}
