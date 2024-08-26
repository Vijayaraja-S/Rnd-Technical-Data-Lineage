package com.p3.poc.parser.parsing.handler.query_spec;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.group.BaseGroupElementInfo;
import com.p3.poc.parser.bean.group.GroupQueryInfo;
import com.p3.poc.parser.bean.group.identifier.GroupType;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import com.p3.poc.parser.parsing.handler.expression.CommonExpressionHandler;
import io.trino.sql.tree.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupByHandler implements CommonQueryParser {
    private QueryParsedDetails queryParsedDetails;
    private CommonExpressionHandler commonExpressionHandler;

    public GroupByHandler(QueryParsedDetails queryParsedDetails) {
        this.queryParsedDetails = queryParsedDetails;
        this.commonExpressionHandler = new CommonExpressionHandler();
    }


    @Override
    public void processQuery(Object node) {
        final GroupBy groupBy = (GroupBy) node;
        final List<BaseGroupElementInfo> groupElementInfoList = groupBy.getGroupingElements()
                .stream()
                .map(this::handlerGroupElements)
                .toList();
        final GroupQueryInfo queryInfo = GroupQueryInfo.builder()
                .isDistinct(groupBy.isDistinct())
                .groupElementInfos(groupElementInfoList)
                .build();
        queryParsedDetails.setGroupQueryInfo(queryInfo);
    }

    private BaseGroupElementInfo handlerGroupElements(GroupingElement groupingElement) {
        List<BaseExpressionInfo> expressionInfos = new ArrayList<>();
        final BaseGroupElementInfo bean = BaseGroupElementInfo.getBean();
        setGroupElementType(groupingElement, bean);
        if (groupingElement instanceof GroupingSets) {
            // need to handle

        } else {
            for (Expression expression : groupingElement.getExpressions()) {
                final BaseExpressionInfo baseExpressionInfo = commonExpressionHandler.handleExpression(expression);
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
