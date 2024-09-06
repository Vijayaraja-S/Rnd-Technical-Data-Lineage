package com.p3.poc.parser.parsing.handler.query_body.service_impl;

import com.p3.poc.parser.bean.query.query_body.QueryBodyType;
import com.p3.poc.parser.bean.query.query_body.QuerySpecificationDetails;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.HavingQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.WhereQueryInfo;
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
    public QuerySpecificationDetails handleQuerySpecification(QuerySpecification querySpecification) {
        final QuerySpecificationDetails querySpec = new QuerySpecificationDetails();
        var children = querySpecification.getChildren();
        if (!children.isEmpty()) {
            children
                    .forEach(child -> {
                        if (child instanceof Select select) {
                            querySpecHandler.handleSelect(select);
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

    public HavingQueryInfo getHavingQueryInfo(QuerySpecification querySpecNode) {
        final Optional<Expression> having = querySpecNode.getHaving();
        if (having.isPresent()) {
            final Expression havingValue = having.get();
            return querySpecHandler.handleHaving(havingValue);
        }
        return HavingQueryInfo.getBean();
    }

    public WhereQueryInfo getWhereQueryInfo(QuerySpecification querySpecNode) {
        final Optional<Expression> where = querySpecNode.getWhere();
        if (where.isPresent()) {
            final Expression whereExp = where.get();
            return querySpecHandler.handleWhere(whereExp);
        }
        return WhereQueryInfo.getBean();
    }

}
