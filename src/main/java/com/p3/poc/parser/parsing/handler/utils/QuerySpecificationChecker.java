package com.p3.poc.parser.parsing.handler.utils;

import com.p3.poc.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import com.p3.poc.parser.parsing.handler.query_specification.service_impl.*;
import io.trino.sql.tree.*;

public class QuerySpecificationChecker {
    
    private QuerySpecificationChecker() {}

    public static AbstractQuerySpecHandler check(Node node) {
        if (node instanceof Select select) {
            return new SelectHandler(select);
        } else if (node instanceof Relation relation) {
            return new FromHandler(relation);
        } else if (node instanceof GroupBy groupBy) {
            return new GroupByHandler(groupBy);
        } else if (node instanceof OrderBy orderBy) {
            return new OrderByHandler(orderBy);
        } else if (node instanceof Limit limit) {
            return new LimitHandler(limit);
        } else if (node instanceof Offset offset) {
            return new OffsetHandler(offset);
        }
        return null;
    }
}
