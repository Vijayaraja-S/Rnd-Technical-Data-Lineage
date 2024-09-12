package com.p3.poc.parser.parsing.handler.query.service_impl;

import com.p3.poc.parser.parsing.handler.query.service.OtherQueryHandler;
import com.p3.poc.parser.parsing.handler.query_body.QueryBodyProcessor;
import com.p3.poc.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import com.p3.poc.parser.parsing.handler.utils.QuerySpecificationChecker;
import io.trino.sql.tree.*;

public class OtherQueryHandleImpl implements OtherQueryHandler {
    private final QueryBodyProcessor queryBodyProcessor;

    public OtherQueryHandleImpl() {
        this.queryBodyProcessor=new QueryBodyProcessor();
    }

    @Override
    public void handleQueryBody(QueryBody queryBody) {
        queryBodyProcessor.handleQueryBody(queryBody);
    }

    @Override
    public void handleOrderBy(Node node) {
        final AbstractQuerySpecHandler handler = QuerySpecificationChecker.check(node);
        if (handler != null) {
            handler.process();
        }
    }

    @Override
    public void handleOffset(Node node) {
        final AbstractQuerySpecHandler handler = QuerySpecificationChecker.check(node);
        if (handler != null) {
            handler.process();
        }

    }

    @Override
    public void handleLimit(Node node) {
        final AbstractQuerySpecHandler handler = QuerySpecificationChecker.check(node);
        if (handler != null) {
            handler.process();
        }

    }
}
