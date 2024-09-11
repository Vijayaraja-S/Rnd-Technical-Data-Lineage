package com.p3.poc.parser.parsing.handler.expression.service_impl.where;

import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.bean.WhereExpressionInfo;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WhereHelper {
    private final ExpressionHandler expressionHandler;

    public WhereHelper() {
        expressionHandler = new ExpressionHandler();
    }

    public void processChildExpressions(Expression expression) {
        for (Node child : expression.getChildren()) {
            if (child instanceof Expression exp) {
                processNestedExpression(WhereExpressionInfo.builder().build(), exp);
            }
        }
    }

    public void processNestedExpression(WhereExpressionInfo whereExpression, Expression exp) {
        expressionHandler.handleExpression(exp, NodeType.WHERE, whereExpression);
    }


    public void addWhereDetails(WhereExpressionInfo whereExpression) {
        final GlobalCollector instance = GlobalCollector.getInstance();
        final String selectId = instance.getDynamicSelectId();
        final Map<String, List<WhereExpressionInfo>> whereInfoMap = instance.getWhereInfoMap();
        if (whereInfoMap.containsKey(selectId)) {
            whereInfoMap.get(selectId).add(whereExpression);
        } else {
            List<WhereExpressionInfo> whereInfoList = new ArrayList<>();
            whereInfoList.add(whereExpression);
            whereInfoMap.put(selectId, whereInfoList);
        }
    }
}
