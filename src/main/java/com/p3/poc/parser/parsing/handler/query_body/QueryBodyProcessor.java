package com.p3.poc.parser.parsing.handler.query_body;

import com.p3.poc.parser.bean.query.query_body.QueryBodyType;
import com.p3.poc.parser.bean.query.query_body.QuerySpecificationDetails;
import com.p3.poc.parser.parsing.handler.query_specification.QuerySpecHandler;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueryBodyProcessor extends QueryBodyHelper {
    private final QuerySpecHandler querySpecHandler;

    public QueryBodyProcessor() {
        this.querySpecHandler = new QuerySpecHandler();
    }

    public QuerySpecificationDetails processQuerySpec(QuerySpecification querySpecification) {
        final QuerySpecificationDetails querySpec = new QuerySpecificationDetails();
        var children = querySpecification.getChildren();
        if (!children.isEmpty()) {
            children
                    .forEach(child -> {
                        if (child instanceof Select select) {
                            querySpec.setSelectQueryInfo(querySpecHandler.handleSelect(select));
                        } else if (child instanceof Relation relation) {
                            querySpec.setBaseRelationInfo(querySpecHandler.handleFrom(relation));
                        } else if (child instanceof GroupBy groupBy) {
                            querySpec.setGroupQueryInfo(querySpecHandler.handleGroupBy(groupBy));
                        } else if (child instanceof OrderBy orderBy) {
                            querySpec.setOrderByInfo(querySpecHandler.handleOrderBY(orderBy));
                        } else if (child instanceof Limit limit) {
                            querySpec.setLimitInfo(querySpecHandler.handleLimit(limit));
                        } else if (child instanceof Offset offset) {
                            querySpec.setOffsetInfo(querySpecHandler.handleOffset(offset));
                        }
                    });
            querySpec.setWhereQueryInfo(getWhereQueryInfo(querySpecification));
            querySpec.setHavingQueryInfo(getHavingQueryInfo(querySpecification));
            querySpec.setQueryBodyType(QueryBodyType.QUERY_SPECIFICATION);
        }
        return querySpec;
    }
}
