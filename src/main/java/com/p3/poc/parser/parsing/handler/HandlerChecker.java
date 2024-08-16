package com.p3.poc.parser.parsing.handler;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.parsing.handler.query.QueryProcessor;
import com.p3.poc.parser.parsing.handler.query.identifier.QueryType;
import com.p3.poc.parser.parsing.handler.query.operations.*;
import io.trino.sql.tree.Node;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class HandlerChecker {
    private QueryParsedDetails queryDetails;

    public HandlerChecker() {
        this.queryDetails = new QueryParsedDetails();
    }

    public QueryProcessor checkQueryObjectHandler(Node node) {
        final QueryType queryType = QueryType.getQueryType(node);
        return switch (queryType) {
            case QUERY_BODY -> new QueryBodyHandler(queryDetails);
            case WITH -> new WithHandler(queryDetails);
            case LIMIT -> new LimitHandler(queryDetails);
            case OFFSET -> new OffsetHandler(queryDetails);
            case ORDER_BY -> new OrderByHandler(queryDetails);
        };
    }
}
