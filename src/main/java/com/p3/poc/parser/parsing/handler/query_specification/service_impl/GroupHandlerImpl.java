package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.group.BaseGroupElementInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.group.GroupQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.group.identifier.GroupType;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.query_specification.service.GroupByNodeHandler;
import io.trino.sql.tree.*;

import java.util.ArrayList;
import java.util.List;

public class GroupHandlerImpl implements GroupByNodeHandler {
    private final ExpressionHandler expressionHandler ;

    public GroupHandlerImpl() {
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public GroupQueryInfo processGroupNode(GroupBy groupBy) {
        final List<BaseGroupElementInfo> groupElementInfoList = groupBy.getGroupingElements()
                .stream()
                .map(this::handlerGroupElements)
                .toList();
        final GroupQueryInfo bean = GroupQueryInfo.getBean();
        bean.setDistinct(groupBy.isDistinct());
        bean.setGroupElementInfos(groupElementInfoList);
        return bean;
    }

    BaseGroupElementInfo handlerGroupElements(GroupingElement groupingElement) {
        List<BaseExpressionInfo> expressionInfos = new ArrayList<>();
        final BaseGroupElementInfo bean = BaseGroupElementInfo.getBean();
        setGroupElementType(groupingElement, bean);
        if (groupingElement instanceof GroupingSets) {
            // need to handle

        } else {
            for (Expression expression : groupingElement.getExpressions()) {
                final BaseExpressionInfo baseExpressionInfo = expressionHandler.handleExpression(expression, ColumnDetails.builder().build());
                if (baseExpressionInfo != null) {
                    expressionInfos.add(baseExpressionInfo);
                }
            }
            bean.setElementExpressionInfoList(expressionInfos);
        }
        return bean;
    }

    private void setGroupElementType(GroupingElement groupingElement, BaseGroupElementInfo bean) {
        if (groupingElement instanceof Cube) {
            bean.setType(GroupType.CUBE);
        } else if (groupingElement instanceof SimpleGroupBy) {
            bean.setType(GroupType.SIMPLE_GROUP_BY);
        } else if (groupingElement instanceof Rollup) {
            bean.setType(GroupType.ROLL_UP);
        }
    }
}
