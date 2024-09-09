package com.p3.poc.parser.parsing.handler.query_body.service_impl;

import com.p3.poc.parser.parsing.handler.query_body.service.QuerySpecificationHandler;
import com.p3.poc.parser.parsing.handler.query_specification.QuerySpecHandler;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class QuerySpecificationImpl implements QuerySpecificationHandler {
    private final QuerySpecHandler querySpecHandler;

    public QuerySpecificationImpl() {
        this.querySpecHandler = new QuerySpecHandler();
    }

    @Override
    public void handleQuerySpecification(QuerySpecification querySpecification) {

        var children = querySpecification.getChildren();
        if (!children.isEmpty()) {
            children
                    .forEach(child -> {
                        if (child instanceof Select select) {
                            querySpecHandler.handleSelect(select);
                        } else if (child instanceof Relation relation) {
                            querySpecHandler.handleFrom(relation);
                        } else if (child instanceof GroupBy groupBy) {
                            querySpecHandler.handleGroupBy(groupBy);
                        } else if (child instanceof OrderBy orderBy) {
                            querySpecHandler.handleOrderBY(orderBy);
                        } else if (child instanceof Limit limit) {
                            querySpecHandler.handleLimit(limit);
                        } else if (child instanceof Offset offset) {
                            querySpecHandler.handleOffset(offset);
                        }
                    });
            getWhereQueryInfo(querySpecification);
            getHavingQueryInfo(querySpecification);
        }
    }

    public void getHavingQueryInfo(QuerySpecification querySpecNode) {
        final Optional<Expression> having = querySpecNode.getHaving();
        if (having.isPresent()) {
            final Expression havingValue = having.get();
            querySpecHandler.handleHaving(havingValue);
        }
    }

    public void getWhereQueryInfo(QuerySpecification querySpecNode) {
        final Optional<Expression> where = querySpecNode.getWhere();
        if (where.isPresent()) {
            final Expression whereExp = where.get();
            querySpecHandler.handleWhere(whereExp);
        }
    }

}
