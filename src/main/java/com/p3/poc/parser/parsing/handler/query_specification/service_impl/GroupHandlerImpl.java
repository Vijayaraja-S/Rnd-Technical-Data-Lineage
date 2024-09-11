package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.lineage.bean.flow.db_objs.GroupInfo;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.expression.ExpressionType;
import com.p3.poc.parser.parsing.handler.query_specification.service.GroupByNodeHandler;
import io.trino.sql.tree.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GroupHandlerImpl implements GroupByNodeHandler {
    private static final Logger log = LoggerFactory.getLogger(GroupHandlerImpl.class);
    private final ExpressionHandler expressionHandler;

    public GroupHandlerImpl() {
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public void processGroupNode(GroupBy groupBy) {
        final GlobalCollector instance = GlobalCollector.getInstance();
        instance.setDynamicGroupId("Grp: "+ UUID.randomUUID());
        final List<? extends Node> children = groupBy.getChildren();
        final List<GroupInfo> groupInfos = new ArrayList<>();
        children.forEach(child -> {
            if (child instanceof GroupingSets groupingSets) {
                log.error("Grouping sets: {}", groupingSets);
            } else {
                handlerGroupElements((GroupingElement) child,groupInfos);
            }
        });
        final Map<String, List<GroupInfo>> groupInfoMap = instance.getGroupInfoMap();
        groupInfoMap.put(instance.getDynamicSelectId(), groupInfos);
    }

    public void handlerGroupElements(GroupingElement groupingElement, List<GroupInfo> groupInfos) {

        final List<? extends Node> children = groupingElement.getChildren();


        children.forEach(child -> {
            if (child instanceof Expression expression) {
                final Object obj = expressionHandler.handleExpression(expression, ExpressionType.GROUP_BY, null);
                if (obj instanceof  ColumnDetails columnDetails){
                    expressionHandler.saveColumnDetails(columnDetails,ExpressionType.GROUP_BY);
                    groupInfos.add(GroupInfo.builder()
                            .tableDetails(columnDetails.getColumnSource())
                            .columnDetails(columnDetails.getColumnName())
                            .build());
                }
            }
        });

    }


}
