package com.p3.poc.parser.parsing.handler.query.identifier;

import io.trino.sql.tree.*;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum QueryType {

    WITH(With.class),
    LIMIT(Limit.class),
    OFFSET(Offset.class),
    ORDER_BY(OrderBy.class),
    QUERY_BODY(QueryBody.class);


    private final Class<? extends Node> nodeClass;

    QueryType(Class<? extends Node> nodeClass) {
        this.nodeClass = nodeClass;
    }

    public static QueryType getQueryType(Node node) {
         if (QueryBody.class.isAssignableFrom(node.getClass())) {
            return QUERY_BODY;
        }

         return Stream.of(values())
                .filter(type -> type != QUERY_BODY)
                .filter(type -> type.getNodeClass().isAssignableFrom(node.getClass()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported node type: " + node.getClass().getName()));
    }
}
