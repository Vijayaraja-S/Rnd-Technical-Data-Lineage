package com.p3.poc.parser.parsing.handler.query;

import com.p3.poc.parser.bean.query.query_body.BaseQueryBodyInfo;
import com.p3.poc.parser.bean.query.with.WithInfo;
import com.p3.poc.parser.parsing.handler.query_body.QueryBodyHandler;
import io.trino.sql.tree.QueryBody;
import io.trino.sql.tree.With;

public class QueryProcessor {
    private final QueryBodyHandler queryBodyHandler;

    public QueryProcessor() {
        this.queryBodyHandler = new QueryBodyHandler();
    }

    public BaseQueryBodyInfo processQueryBody(QueryBody queryBody) {
        return queryBodyHandler.handleQueryBody(queryBody);
    }

    public WithInfo processWith(With with) {
        final WithInfo build = WithInfo.builder()
                .isrecursive(with.isRecursive())
                .build();
        return null;
    }
}
