package com.p3.poc.parser.parsing.handler.query_specification;

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

    public void handleSelect(Node node) {
        final Select select = (Select) node;
        selectNodeHandler.processSelectNode(select);
    }

    public void handleFrom(Node node) {
        final Relation relation = (Relation) node;
        otherQuerySpecImpl.processFromNode(relation);
    }

    public void handleGroupBy(Node node) {
        final GroupBy groupBy = (GroupBy) node;
        groupByNodeHandler.processGroupNode(groupBy);
    }

    public void handleOrderBY(Node node) {
        final OrderBy orderBy = (OrderBy) node;
        otherQuerySpecImpl.processOrderByNode(orderBy);
    }

    public void handleOffset(Node node) {
        final Offset offset = (Offset) node;
        otherQuerySpecImpl.processOffsetNode(offset);
    }

    public void handleLimit(Node node) {
        final Limit limit = (Limit) node;
        otherQuerySpecImpl.processLimitNode(limit);
    }

    public void handleHaving(Expression expression) {
        otherQuerySpecImpl.processHavingNode(expression);
    }

    public void handleWhere(Expression expression) {
        otherQuerySpecImpl.processWhereNode(expression);
    }
}
