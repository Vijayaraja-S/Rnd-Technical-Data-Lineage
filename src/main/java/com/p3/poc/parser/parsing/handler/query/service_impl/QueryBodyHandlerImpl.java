package com.p3.poc.parser.parsing.handler.query.service_impl;

import com.p3.poc.parser.bean.query.query_body.BaseQueryBodyInfo;
import com.p3.poc.parser.parsing.handler.query.service.QueryBodyHandler;
import com.p3.poc.parser.parsing.handler.query_body.QueryBodyProcessor;
import com.p3.poc.parser.parsing.handler.query_body.service_impl.QuerySpecificationImpl;
import io.trino.sql.tree.QueryBody;

public class QueryBodyHandlerImpl implements QueryBodyHandler {

    private final QueryBodyProcessor queryBodyChecker;

    public QueryBodyHandlerImpl() {
        this.queryBodyChecker = new QueryBodyProcessor(new QuerySpecificationImpl());
    }

    @Override
    public BaseQueryBodyInfo handleQueryBody(QueryBody queryBody) {
        return queryBodyChecker.handleQueryBody(queryBody);
    }
}
