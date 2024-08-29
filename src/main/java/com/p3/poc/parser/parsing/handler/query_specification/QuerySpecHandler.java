package com.p3.poc.parser.parsing.handler.query_specification;

import com.p3.poc.parser.bean.relation.BaseRelationInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.group.GroupQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.HavingQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.WhereQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.select.SelectQueryInfo;
import io.trino.sql.tree.*;

public class QuerySpecHandler {
    private final QuerySpecProcessor queryProcessor;


    public QuerySpecHandler() {
        this.queryProcessor = new QuerySpecProcessor();
    }

    public SelectQueryInfo handleSelect(Node node) {
        final Select select = (Select) node;
        return queryProcessor.processSelectNode(select);

    }

    public BaseRelationInfo handleFrom(Node node) {
        final Relation relation = (Relation) node;
        return queryProcessor.processFromNode(relation);
    }

    public GroupQueryInfo handleGroupBy(Node node) {
        final GroupBy groupBy = (GroupBy) node;
        return queryProcessor.processGroupNode(groupBy);
    }

    public OrderByInfo handleOrderBY(Node node) {
        final OrderBy orderBy = (OrderBy) node;
        return queryProcessor.processOrderByNode(orderBy);
    }

    public OffsetInfo handleOffset(Node node) {
        final Offset offset = (Offset) node;
        return queryProcessor.processOffsetNode(offset);
    }

    public LimitInfo handleLimit(Node node) {
        final Limit limit = (Limit) node;
        return queryProcessor.processLimitNode(limit);
    }

    public HavingQueryInfo handleHaving(Expression expression) {
        return queryProcessor.processHavingNode(expression);
    }

    public WhereQueryInfo handleWhere(Expression expression) {
        return queryProcessor.processWhereNode(expression);
    }
}
