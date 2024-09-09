package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.query_specification.service.GroupByNodeHandler;
import io.trino.sql.tree.GroupBy;
import io.trino.sql.tree.GroupingElement;

public class GroupHandlerImpl implements GroupByNodeHandler {
    private final ExpressionHandler expressionHandler ;

    public GroupHandlerImpl() {
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public void processGroupNode(GroupBy groupBy) {

    }

    public void handlerGroupElements(GroupingElement groupingElement) {

    }

    private void setGroupElementType(GroupingElement groupingElement) {
    }
}
