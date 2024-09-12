package com.p3.poc.parser.parsing.handler.utils;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.bean.parsing_details.JoinDetailsInfo;
import com.p3.poc.parser.bean.parsing_details.OrderByInfo;
import com.p3.poc.parser.parsing.handler.expression.bean.HavingExpressionInfo;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.bean.WhereExpressionInfo;
import com.p3.poc.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionProcessor;

import com.p3.poc.parser.parsing.handler.expression.service_impl.having.HavingProcessor;
import com.p3.poc.parser.parsing.handler.expression.service_impl.join.JoinProcessor;
import com.p3.poc.parser.parsing.handler.expression.service_impl.order_by.OrderByProcessor;
import com.p3.poc.parser.parsing.handler.expression.service_impl.where.WhereProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ExpressionHandleChecker {
    private ExpressionHandleChecker() {
    }

    private static final Map<NodeType, Function<Object, AbstractExpressionProcessor>> processorMap = new HashMap<>();

    static {
        processorMap.put(NodeType.ORDER, ExpressionHandleChecker::createOrderByProcessor);
        processorMap.put(NodeType.WHERE, ExpressionHandleChecker::createWhereProcessor);
        processorMap.put(NodeType.HAVING, ExpressionHandleChecker::createHavingProcessor);
        processorMap.put(NodeType.JOIN,ExpressionHandleChecker::createJoinProcessor );
        processorMap.put(NodeType.SELECT,ExpressionHandleChecker::createSelectProcessor);
        processorMap.put(NodeType.GROUP_BY,ExpressionHandleChecker::createGroupByProcessor);
        processorMap.put(NodeType.OFFSET,ExpressionHandleChecker::createOffsetProcessor);
        processorMap.put(NodeType.LIMIT,ExpressionHandleChecker::createLimitProcessor);
    }

    public static AbstractExpressionProcessor checkExpression(NodeType nodeType, Object commonBean) {
        Function<Object, AbstractExpressionProcessor> processorFunction = processorMap.get(nodeType);
        if (processorFunction != null) {
            return processorFunction.apply(commonBean);
        }
        throw new IllegalArgumentException("No processor found for NodeType: " + nodeType);
    }

    private static AbstractExpressionProcessor createLimitProcessor(Object commonBean) {
        return null;
    }

    private static AbstractExpressionProcessor createOffsetProcessor(Object commonBean) {
        return null;
    }


    private static AbstractExpressionProcessor createGroupByProcessor(Object commonBean) {
    }

    private static AbstractExpressionProcessor createSelectProcessor(Object commonBean) {
        if (commonBean instanceof ColumnDetails) {
            return new JoinProcessor(joinDetailsInfo);
        } else {
            return new JoinProcessor(JoinDetailsInfo.builder().build());
        }
    }

    private static AbstractExpressionProcessor createJoinProcessor(Object commonBean) {
        if (commonBean instanceof JoinDetailsInfo joinDetailsInfo) {
            return new JoinProcessor(joinDetailsInfo);
        } else {
            return new JoinProcessor(JoinDetailsInfo.builder().build());
        }
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
