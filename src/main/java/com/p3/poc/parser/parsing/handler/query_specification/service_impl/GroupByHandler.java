package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.bean.parsing_details.GroupInfo;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.expression.utils.ExpressionUtils;
import com.p3.poc.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class GroupByHandler extends AbstractQuerySpecHandler {
    private final ExpressionUtils expressionUtils;
    private final ExpressionHandler expressionHandler;
    private final GroupBy groupBy;

    public GroupByHandler(GroupBy node) {
        this.groupBy = node;
        this.expressionHandler = new ExpressionHandler();
        this.expressionUtils = new ExpressionUtils(NodeType.GROUP_BY);
    }

    @Override
    public void process() {
        final GlobalCollector instance = GlobalCollector.getInstance();

        instance.setDynamicGroupId("Grp: " + UUID.randomUUID());
        final List<? extends Node> children = groupBy.getChildren();
        final List<GroupInfo> groupInfos = new ArrayList<>();
        children.forEach(child -> {
            if (child instanceof GroupingSets groupingSets) {
                log.error("Grouping sets: {}", groupingSets);
            } else {
                handlerGroupElements((GroupingElement) child, groupInfos);
            }
        });
        final Map<String, List<GroupInfo>> groupInfoMap = instance.getGroupInfoMap();
        groupInfoMap.put(instance.getDynamicSelectId(), groupInfos);
    }

    public void handlerGroupElements(GroupingElement groupingElement, List<GroupInfo> groupInfos) {
        final List<? extends Node> children = groupingElement.getChildren();

        children.forEach(child -> {
            if (child instanceof Expression expression) {
                final ColumnDetails columnDetails = ColumnDetails.builder().build();
                expressionHandler.handleExpression(expression, NodeType.GROUP_BY, GroupInfo.builder().build());
                groupInfos.add(GroupInfo.builder()
                        .tableDetails(columnDetails.getColumnSource())
                        .columnDetails(columnDetails.getColumnName())
                        .build());
            }
        });
    }
}
