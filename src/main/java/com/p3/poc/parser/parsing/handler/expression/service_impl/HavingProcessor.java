package com.p3.poc.parser.parsing.handler.expression.service_impl;

import com.p3.poc.lineage.bean.flow.db_objs.ConditionType;
import com.p3.poc.lineage.bean.flow.db_objs.HavingExpressionInfo;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.expression.ExpressionType;
import com.p3.poc.parser.parsing.handler.expression.service.Comparison;
import com.p3.poc.parser.parsing.handler.expression.service.FunctionCallExpression;
import io.trino.sql.tree.ComparisonExpression;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.FunctionCall;
import io.trino.sql.tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HavingProcessor implements FunctionCallExpression, Comparison {
    private final ExpressionHandler expressionHandler;
    private final Object commonBean;

    public HavingProcessor(Object commonBean) {
        this.commonBean = commonBean;
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public void processFunctionExpression(FunctionCall functionCall) {
        final HavingExpressionInfo havingExpressionInfo = extractHavingExpressionInfo(commonBean);
        havingExpressionInfo.setMultiRowFunction(true);
        havingExpressionInfo.setLeft(String.valueOf(functionCall.getName()));
     }

    @Override
    public Object processComparisonExpression(ComparisonExpression comparisonExpression) {
        final List<Node> children = comparisonExpression.getChildren();
        final ComparisonExpression.Operator operator = comparisonExpression.getOperator();
        final HavingExpressionInfo havingExpressionInfo = extractHavingExpressionInfo(commonBean);
        havingExpressionInfo.setExpressionContent(comparisonExpression.toString());
        havingExpressionInfo.setConditionType(ConditionType.COMPARISON);
        havingExpressionInfo.setOperator(operator.toString());
        children.forEach(
                child -> {
                    if (child instanceof Expression expression) {
                        expressionHandler.handleExpression(expression, ExpressionType.HAVING,havingExpressionInfo);
                    }
                }
        );
      addHavingDetails(havingExpressionInfo);
        return null;
    }
    private HavingExpressionInfo extractHavingExpressionInfo(Object bean) {
        if (bean instanceof HavingExpressionInfo havingExpressionInfo) {
            return havingExpressionInfo;
        }
        return HavingExpressionInfo.builder().build();
    }
    private void addHavingDetails(HavingExpressionInfo havingExpressionInfo) {
        final GlobalCollector instance = GlobalCollector.getInstance();
        final String groupId = instance.getDynamicGroupId();
        final Map<String, List<HavingExpressionInfo>> havingInfoMap = GlobalCollector.getInstance().getHavingInfoMap();
        if (havingInfoMap.containsKey(groupId)) {
            havingInfoMap.get(groupId).add(havingExpressionInfo);
        } else {
            List<HavingExpressionInfo> havingExpressionInfos = new ArrayList<>();
            havingExpressionInfos.add(havingExpressionInfo);
            havingInfoMap.put(groupId, havingExpressionInfos);
        }
    }
}
