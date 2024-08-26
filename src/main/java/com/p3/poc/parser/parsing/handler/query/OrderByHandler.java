package com.p3.poc.parser.parsing.handler.query;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.others.OrderByInfo;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import com.p3.poc.parser.parsing.handler.expression.CommonExpressionHandler;
import io.trino.sql.tree.OrderBy;
import io.trino.sql.tree.SortItem;

import java.util.List;

public class OrderByHandler implements CommonQueryParser {
    private final QueryParsedDetails queryDetails;
    private final OrderByInfo orderByInfo;
    private final CommonExpressionHandler commonExpressionHandler;

    public OrderByHandler(QueryParsedDetails queryDetails) {
        this.queryDetails = queryDetails;
        this.orderByInfo = new OrderByInfo();
        this.commonExpressionHandler = new CommonExpressionHandler();
    }


    @Override
    public void processQuery(Object node) {
        final OrderBy orderBy = (OrderBy) node;
        final List<OrderByInfo.SortInfo> sortInfoList = orderBy.getSortItems()
                .stream()
                .map(this::processSortItems)
                .toList();
        orderByInfo.setSortInfos(sortInfoList);
        queryDetails.setOrderByInfo(orderByInfo);
    }

    private OrderByInfo.SortInfo processSortItems(SortItem sortItem) {
        return OrderByInfo.SortInfo
                .builder()
                .expressionInfo(commonExpressionHandler.handleExpression(sortItem.getSortKey()))
                .normalOrder(sortItem.getOrdering())
                .nullOrder(sortItem.getNullOrdering())
                .build();
    }
}
