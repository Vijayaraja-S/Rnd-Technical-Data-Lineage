package com.p3.poc.parser.parsing.handler.query.service_impl;

import com.p3.poc.parser.parsing.handler.query.service.OtherQueryHandler;
import com.p3.poc.parser.parsing.handler.query_body.QueryBodyProcessor;
import com.p3.poc.parser.parsing.handler.query_specification.service.OtherSpecHandler;
import com.p3.poc.parser.parsing.handler.query_specification.service_impl.OtherQuerySpecImpl;
import io.trino.sql.tree.*;

public class OtherQueryHandleImpl implements OtherQueryHandler {
    private final QueryBodyProcessor queryBodyProcessor;
    private final OtherSpecHandler otherSpecHandler;
    public OtherQueryHandleImpl() {
        this.queryBodyProcessor=new QueryBodyProcessor();
        this.otherSpecHandler=new OtherQuerySpecImpl();
    }

    @Override
    public void handleQueryBody(QueryBody queryBody) {
        queryBodyProcessor.handleQueryBody(queryBody);
    }

    @Override
    public void handleOrderBy(Node node) {
        final OrderBy orderBy = (OrderBy) node;
        otherSpecHandler.processOrderByNode(orderBy);
    }

    @Override
    public void handleOffset(Node node) {
        final Offset offset = (Offset) node;
        otherSpecHandler.processOffsetNode(offset);
    }

    @Override
    public void handleLimit(Node node) {
        final Limit limit = (Limit) node;
        otherSpecHandler.processLimitNode(limit);
    }
}
