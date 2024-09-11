package com.p3.poc.parser.parsing.handler.expression.service;

import com.p3.poc.lineage.bean.flow.db_objs.OrderByInfo;
import com.p3.poc.parser.parsing.handler.expression.bean.HavingExpressionInfo;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.bean.WhereExpressionInfo;
import com.p3.poc.parser.parsing.handler.expression.service_impl.group_by.GroupByProcessor;
import com.p3.poc.parser.parsing.handler.expression.service_impl.having.HavingProcessor;
import com.p3.poc.parser.parsing.handler.expression.service_impl.join.JoinProcessor;
import com.p3.poc.parser.parsing.handler.expression.service_impl.order_by.OrderByProcessor;
import com.p3.poc.parser.parsing.handler.expression.service_impl.select.SelectProcessor;
import com.p3.poc.parser.parsing.handler.expression.service_impl.where.WhereProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ExpressionHandleChecker {
    private ExpressionHandleChecker() {
    }

    private static final Map<NodeType, Function<Object, AbstractExpressionProcessor>> processorMap = new HashMap<>();

    static {
        processorMap.put(NodeType.JOIN, commonBean -> new JoinProcessor());
        processorMap.put(NodeType.SELECT, commonBean -> new SelectProcessor());
        processorMap.put(NodeType.GROUP_BY, commonBean -> new GroupByProcessor());
        processorMap.put(NodeType.ORDER, ExpressionHandleChecker::createOrderByProcessor);
        processorMap.put(NodeType.WHERE, ExpressionHandleChecker::createWhereProcessor);
        processorMap.put(NodeType.HAVING, ExpressionHandleChecker::createHavingProcessor);
    }

    public static AbstractExpressionProcessor checkExpression(NodeType nodeType, Object commonBean) {
        Function<Object, AbstractExpressionProcessor> processorFunction = processorMap.get(nodeType);
        if (processorFunction != null) {
            return processorFunction.apply(commonBean);
        }
        throw new IllegalArgumentException("No processor found for NodeType: " + nodeType);
    }

    private static AbstractExpressionProcessor createOrderByProcessor(Object commonBean) {
        if (commonBean instanceof OrderByInfo orderByInfo) {
            return new OrderByProcessor(orderByInfo);
        } else {
            return new OrderByProcessor(OrderByInfo.builder().build());
        }
    }

    private static AbstractExpressionProcessor createHavingProcessor(Object commonBean) {
        if (commonBean instanceof HavingExpressionInfo havingExpressionInfo) {
            return new HavingProcessor(havingExpressionInfo);
        } else {
            return new HavingProcessor(HavingExpressionInfo.builder().build());
        }
    }

    private static AbstractExpressionProcessor createWhereProcessor(Object commonBean) {
        if (commonBean instanceof WhereExpressionInfo whereExpressionInfo) {
            return new WhereProcessor(whereExpressionInfo);
        } else {
            return new WhereProcessor(WhereExpressionInfo.builder().build());
        }
    }
}
