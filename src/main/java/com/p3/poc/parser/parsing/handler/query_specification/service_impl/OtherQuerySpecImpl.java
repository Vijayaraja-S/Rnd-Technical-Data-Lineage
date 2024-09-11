package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.lineage.bean.flow.db_objs.*;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.query_specification.service.OtherSpecHandler;
import com.p3.poc.parser.parsing.handler.relation.RelationHandler;
import io.trino.sql.tree.*;

import java.util.List;
import java.util.Map;

public class OtherQuerySpecImpl implements OtherSpecHandler {
    private final RelationHandler relationHandler;
    private final ExpressionHandler expressionHandler;

    public OtherQuerySpecImpl() {
        this.relationHandler = new RelationHandler();
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public void processFromNode(Relation relation) {
        final TableDetails table = TableDetails.builder().build();
        relationHandler.handleRelation(relation, table);
    }


    @Override
    public void processWhereNode(Expression whereValue) {
        expressionHandler.handleExpression(whereValue, NodeType.WHERE,null);
    }

    @Override
    public void processHavingNode(Expression havingValue) {
        expressionHandler.handleExpression(havingValue, NodeType.HAVING,null);
    }

    @Override
    public void processOrderByNode(OrderBy orderBy) {
        final List<SortItem> sortItems = orderBy.getSortItems();
        sortItems.forEach(sortItem -> {
            final OrderByInfo orderByInfo = OrderByInfo.builder()
                    .orderType(sortItem.getOrdering().toString())
                    .build();
            final Expression sortKey = sortItem.getSortKey();
            expressionHandler.handleExpression(sortKey, NodeType.ORDER,orderByInfo);
        });
    }

    @Override
    public void processOffsetNode(Offset offset) {
        final OffsetInfo offsetInfo = OffsetInfo.builder().build();
        expressionHandler.handleExpression(offset.getRowCount(), NodeType.OFFSET, offsetInfo);
        final GlobalCollector instance = GlobalCollector.getInstance();
        final Map<String, OffsetInfo> offsetInfoMap = instance.getOffsetInfoMap();
        offsetInfoMap.put(instance.getDynamicSelectId(), offsetInfo);

    }

    @Override
    public void processLimitNode(Limit limit) {
        final LimitInfo limitInfo = LimitInfo.builder().build();
        expressionHandler.handleExpression(limit.getRowCount(), NodeType.LIMIT,limitInfo );
        final GlobalCollector instance = GlobalCollector.getInstance();
        final Map<String, LimitInfo> limitInfoMap = instance.getLimitInfoMap();
        limitInfoMap.put(instance.getDynamicSelectId(), limitInfo);
    }


}
