package com.p3.poc.parser.parsing.handler.query_body;

import com.p3.poc.parser.bean.query.query_body.BaseQueryBodyInfo;
import com.p3.poc.parser.bean.query.query_body.QuerySpecificationDetails;
import io.trino.sql.tree.QueryBody;
import io.trino.sql.tree.QuerySpecification;

import java.util.Map;
import java.util.function.Function;

public class QueryBodyHandler {
    private final QueryBodyProcessor queryBodyProcessor;

    private final Map<Class<? extends QueryBody>, Function<QueryBody, ? extends BaseQueryBodyInfo>> handlers;


    public QueryBodyHandler() {
        this. queryBodyProcessor = new QueryBodyProcessor();
        this.handlers = Map.of(
                QuerySpecification.class, this::handleQuerySpecification
        );
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseQueryBodyInfo> T handleQueryBody(QueryBody queryBody) {
        final Function<QueryBody, ? extends BaseQueryBodyInfo> handler = handlers.
                getOrDefault(queryBody.getClass(), this::handleUnknownExpression);
        return (T) handler.apply(queryBody);
    }

    private BaseQueryBodyInfo handleUnknownExpression(QueryBody queryBody) {
        return null;
    }

    private QuerySpecificationDetails handleQuerySpecification(QueryBody queryBody) {
        final QuerySpecification querySpecification = (QuerySpecification) queryBody;
        return queryBodyProcessor.processQueryBody(querySpecification);
    }
}
