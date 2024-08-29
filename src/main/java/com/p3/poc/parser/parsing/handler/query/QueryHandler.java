package com.p3.poc.parser.parsing.handler.query;

import com.p3.poc.parser.bean.query.query_body.BaseQueryBodyInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import com.p3.poc.parser.bean.query.with.WithInfo;
import io.trino.sql.tree.Node;
import io.trino.sql.tree.QueryBody;
import io.trino.sql.tree.With;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueryHandler {
    private final QueryProcessor queryProcessor;

    public QueryHandler() {
        this.queryProcessor = new QueryProcessor();
    }

    public WithInfo handleWith(Node node) {
        final With with = (With) node;
        return queryProcessor.processWith(with);
    }

    public BaseQueryBodyInfo handleQueryBody(Node node) {
        final QueryBody queryBody = (QueryBody) node;
        return queryProcessor.processQueryBody(queryBody);
    }

    public OffsetInfo handleOffset(Node node) {
        return null;
    }

    public OffsetInfo handleOrderBy(Node node) {
        return null;
    }

    public LimitInfo handleLimit(Node node) {
        return null;
    }
}
