package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.SortInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.HavingQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.WhereQueryInfo;
import com.p3.poc.parser.bean.relation.BaseRelationInfo;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.query_specification.service.OtherSpecHandler;
import com.p3.poc.parser.parsing.handler.relation.RelationHandler;
import io.trino.sql.tree.*;

import java.util.ArrayList;
import java.util.List;

public class OtherQuerySpecImpl implements OtherSpecHandler {
    private final RelationHandler relationHandler;
    private final ExpressionHandler expressionHandler;

    public OtherQuerySpecImpl() {
        this.relationHandler = new RelationHandler();
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public BaseRelationInfo processFromNode(Relation relation) {
        return relationHandler.handleRelation(relation);
    }


    @Override
    public OffsetInfo processOffsetNode(Offset offset) {
        final BaseExpressionInfo expression = processExpression(offset.getRowCount());
        final OffsetInfo offsetInfo = OffsetInfo.getBean();
        offsetInfo.setExpression(expression);
        return offsetInfo;
    }

    @Override
    public LimitInfo processLimitNode(Limit limit) {
        final BaseExpressionInfo expression = processExpression(limit.getRowCount());
        final LimitInfo limitInfo = LimitInfo.getBean();
        limitInfo.setExpression(expression);
        return limitInfo;
    }

    @Override
    public HavingQueryInfo processHavingNode(Expression havingValue) {
        final BaseExpressionInfo baseExpressionInfo = processExpression(havingValue);
        final HavingQueryInfo havingQueryInfo = HavingQueryInfo.getBean();
        havingQueryInfo.setQueryExpressionInfo(baseExpressionInfo);
        return havingQueryInfo;
    }

    @Override
    public WhereQueryInfo processWhereNode(Expression whereValue) {
        final BaseExpressionInfo baseExpressionInfo = processExpression(whereValue);
        final WhereQueryInfo whereQueryInfo = WhereQueryInfo.getBean();
        whereQueryInfo.setQueryExpressionInfo(baseExpressionInfo);
        return whereQueryInfo;
    }

    @Override
    public OrderByInfo processOrderByNode(OrderBy orderBy) {
        final OrderByInfo orderByInfo = OrderByInfo.getBean();
        final List<SortInfo> sortInfos = new ArrayList<>();
        orderBy.getSortItems().forEach(sortItem ->
                {
                    final SortInfo build = SortInfo.builder()
                            .normalOrder(sortItem.getOrdering())
                            .nullOrder(sortItem.getNullOrdering())
                            .expressionInfo(processExpression(sortItem.getSortKey()))
                            .build();
                    sortInfos.add(build);
                }
        );
        orderByInfo.setSortInfos(sortInfos);
        return orderByInfo;
    }

    private BaseExpressionInfo processExpression(Expression havingValue) {
        return expressionHandler.handleExpression(havingValue);
    }
}
