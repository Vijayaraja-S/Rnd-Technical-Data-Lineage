package com.p3.poc.parser.parsing.handler.query_body;

import com.p3.poc.parser.parsing.handler.query_body.service.QuerySpecificationHandler;
import com.p3.poc.parser.parsing.handler.query_body.service_impl.QuerySpecificationImpl;
import io.trino.sql.tree.QueryBody;
import io.trino.sql.tree.QuerySpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.function.Consumer;
@Slf4j
public class QueryBodyProcessor {
    private final QuerySpecificationHandler querySpecificationHandler;

    private final Map<Class<? extends QueryBody>, Consumer<QueryBody>> handlers;


    public QueryBodyProcessor() {
        this.querySpecificationHandler = new QuerySpecificationImpl();
        this.handlers = Map.of(QuerySpecification.class, this::handleQuerySpecification);
    }

    public void handleQueryBody(QueryBody queryBody) {
        final Consumer<QueryBody> handler = handlers.getOrDefault(queryBody.getClass(), this::handleUnknownExpression);
        handler.accept(queryBody);
    }

    private void handleQuerySpecification(QueryBody queryBody) {
        final QuerySpecification querySpecification = (QuerySpecification) queryBody;
        querySpecificationHandler.handleQuerySpecification(querySpecification);
    }

    private void handleUnknownExpression(QueryBody queryBody) {
        log.error("Unknown query body: {}", queryBody);
    }
}
