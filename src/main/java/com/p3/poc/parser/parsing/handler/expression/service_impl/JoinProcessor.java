package com.p3.poc.parser.parsing.handler.expression.service_impl;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.lineage.bean.flow.db_objs.JoinDetailsInfo;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.expression.ExpressionType;
import com.p3.poc.parser.parsing.handler.expression.service.Comparison;
import io.trino.sql.tree.ComparisonExpression;
import java.util.Map;

public class JoinProcessor implements Comparison {
    private final ExpressionHandler expressionHandler;
    public JoinProcessor() {
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public ColumnDetails processComparisonExpression(ComparisonExpression comparisonExpression) {
        final Object obj1 = expressionHandler.handleExpression(comparisonExpression.getRight(), ExpressionType.JOIN, null);
        final Object obj2 = expressionHandler.handleExpression(comparisonExpression.getLeft(), ExpressionType.JOIN, null);
        if (obj1 instanceof ColumnDetails left && obj2 instanceof ColumnDetails right){
            expressionHandler.saveColumDetails(left,ExpressionType.JOIN);
            expressionHandler.saveColumDetails(right,ExpressionType.JOIN);
            final String joinId = right.getColumnId() + "::" + left.getColumnId();
            final JoinDetailsInfo joinDetailsInfo = saveJoinDetailsInfo(comparisonExpression,joinId);
            setJoinProperties(left, joinId ,joinDetailsInfo);
            setJoinProperties(right, joinId,joinDetailsInfo);
            return left;
        }
        return null;
    }
    public JoinDetailsInfo saveJoinDetailsInfo(io.trino.sql.tree.ComparisonExpression comparisonExpression, String joinId) {
        final Map<String, JoinDetailsInfo> joinDetailsMap = GlobalCollector.getInstance().getJoinDetailsMap();

        final JoinDetailsInfo detailsInfo = JoinDetailsInfo.builder()
                .detailsId("jd:" + joinDetailsMap.size())
                .joinId(joinId)
                .joinCondition(comparisonExpression.toString())
                .joinEquation(comparisonExpression.getOperator().toString())
                .build();
        joinDetailsMap.put(detailsInfo.getDetailsId(), detailsInfo);
        return detailsInfo;
    }

    public void setJoinProperties(ColumnDetails source, String joinId,JoinDetailsInfo joinDetailsInfo) {
        source.setJoin(true);
        source.setJoinId(joinId);
        source.setJoinDetailsId(joinDetailsInfo.getDetailsId());
    }
}
