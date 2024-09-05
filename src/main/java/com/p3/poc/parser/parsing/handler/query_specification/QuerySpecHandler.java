package com.p3.poc.parser.parsing.handler.query_specification;

import com.p3.poc.parser.bean.relation.BaseRelationInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.group.GroupQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.HavingQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.WhereQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.select.SelectQueryInfo;
import com.p3.poc.parser.parsing.handler.query_specification.service.*;
import com.p3.poc.parser.parsing.handler.query_specification.service_impl.GroupHandlerImpl;
import com.p3.poc.parser.parsing.handler.query_specification.service_impl.OtherQuerySpecImpl;
import com.p3.poc.parser.parsing.handler.query_specification.service_impl.SelectHandlerImpl;
import io.trino.sql.tree.*;

public class QuerySpecHandler {
    private final SelectNodeHandler selectNodeHandler;
    private final GroupByNodeHandler groupByNodeHandler;
    private final OtherSpecHandler otherQuerySpecImpl;

    public QuerySpecHandler() {
        this.selectNodeHandler = new SelectHandlerImpl();
        this.groupByNodeHandler = new GroupHandlerImpl();
        this.otherQuerySpecImpl = new OtherQuerySpecImpl();
    }

    public SelectQueryInfo handleSelect(Node node) {
        final Select select = (Select) node;
        return selectNodeHandler.processSelectNode(select);
    }

    public BaseRelationInfo handleFrom(Node node) {
        final Relation relation = (Relation) node;
        return otherQuerySpecImpl.processFromNode(relation);
    }

    public GroupQueryInfo handleGroupBy(Node node) {
        final GroupBy groupBy = (GroupBy) node;
        return groupByNodeHandler.processGroupNode(groupBy);
    }

    public OrderByInfo handleOrderBY(Node node) {
        final OrderBy orderBy = (OrderBy) node;
        return otherQuerySpecImpl.processOrderByNode(orderBy);
    }

    public OffsetInfo handleOffset(Node node) {
        final Offset offset = (Offset) node;
        return otherQuerySpecImpl.processOffsetNode(offset);
    }

    public LimitInfo handleLimit(Node node) {
        final Limit limit = (Limit) node;
        return otherQuerySpecImpl.processLimitNode(limit);
    }

    public HavingQueryInfo handleHaving(Expression expression) {
        return otherQuerySpecImpl.processHavingNode(expression);
    }

    public WhereQueryInfo handleWhere(Expression expression) {
        return otherQuerySpecImpl.processWhereNode(expression);
    }
}
