package com.p3.poc.lineage.parser.parsing.handler.utils;

import com.p3.poc.lineage.parser.bean.parsing_details.*;
import com.p3.poc.lineage.parser.parsing.handler.expression.bean.HavingExpressionInfo;
import com.p3.poc.lineage.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.lineage.parser.parsing.handler.expression.bean.WhereExpressionInfo;
import com.p3.poc.lineage.parser.parsing.handler.expression.service.AbstractExpressionProcessor;
import com.p3.poc.lineage.parser.parsing.handler.expression.service_impl.group_by.GroupByProcessor;
import com.p3.poc.lineage.parser.parsing.handler.expression.service_impl.having.HavingProcessor;
import com.p3.poc.lineage.parser.parsing.handler.expression.service_impl.join.JoinProcessor;
import com.p3.poc.lineage.parser.parsing.handler.expression.service_impl.limit.LimitProcessor;
import com.p3.poc.lineage.parser.parsing.handler.expression.service_impl.offset.OffsetProcessor;
import com.p3.poc.lineage.parser.parsing.handler.expression.service_impl.order_by.OrderByProcessor;
import com.p3.poc.lineage.parser.parsing.handler.expression.service_impl.select.SelectProcessor;
import com.p3.poc.lineage.parser.parsing.handler.expression.service_impl.where.WhereProcessor;

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
        processorMap.put(NodeType.JOIN, ExpressionHandleChecker::createJoinProcessor);
        processorMap.put(NodeType.SELECT, ExpressionHandleChecker::createSelectProcessor);
        processorMap.put(NodeType.GROUP_BY, ExpressionHandleChecker::createGroupByProcessor);
        processorMap.put(NodeType.OFFSET, ExpressionHandleChecker::createOffsetProcessor);
        processorMap.put(NodeType.LIMIT, ExpressionHandleChecker::createLimitProcessor);
    }

    public static AbstractExpressionProcessor checkExpression(NodeType nodeType, Object commonBean) {
        Function<Object, AbstractExpressionProcessor> processorFunction = processorMap.get(nodeType);
        if (processorFunction != null) {
            return processorFunction.apply(commonBean);
        }
        throw new IllegalArgumentException("No processor found for NodeType: " + nodeType);
    }

    private static AbstractExpressionProcessor createLimitProcessor(Object commonBean) {
        if (commonBean instanceof LimitInfo limitInfo) {
            return new LimitProcessor(limitInfo);
        } else {
            return new LimitProcessor(LimitInfo.builder().build());
        }
    }

    private static AbstractExpressionProcessor createOffsetProcessor(Object commonBean) {
        if (commonBean instanceof OffsetInfo offsetInfo) {
            return new OffsetProcessor(offsetInfo);
        } else {
            return new OffsetProcessor(OffsetInfo.builder().build());
        }
    }


    private static AbstractExpressionProcessor createGroupByProcessor(Object commonBean) {
        if (commonBean instanceof GroupInfo groupInfo) {
            return new GroupByProcessor(groupInfo);
        } else {
            return new GroupByProcessor(GroupInfo.builder().build());
        }
    }

    private static AbstractExpressionProcessor createSelectProcessor(Object commonBean) {
        if (commonBean instanceof ColumnDetails columnDetails) {
            return new SelectProcessor(columnDetails);
        } else {
            return new SelectProcessor(ColumnDetails.builder().build());
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
