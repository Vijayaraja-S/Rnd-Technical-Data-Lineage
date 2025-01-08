package com.p3.poc.lineage.parser.parsing.handler.query_body.service_impl;

import com.p3.poc.lineage.parser.parsing.handler.utils.QuerySpecificationChecker;
import com.p3.poc.lineage.parser.parsing.handler.query_body.service.QuerySpecificationHandler;
import com.p3.poc.lineage.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import com.p3.poc.lineage.parser.parsing.handler.query_specification.service_impl.HavingHandler;
import com.p3.poc.lineage.parser.parsing.handler.query_specification.service_impl.WhereHandler;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class QuerySpecificationImpl implements QuerySpecificationHandler {


    @Override
    public void handleQuerySpecification(QuerySpecification querySpecification) {

        var children = querySpecification.getChildren();
        if (!children.isEmpty()) {
            children
                    .forEach(child -> {
                        final AbstractQuerySpecHandler handler = QuerySpecificationChecker.check(child);
                        if (handler != null) {
                            handler.process();
                        }
                    });
            processWhere(querySpecification);
            processHaving(querySpecification);
        }
    }

    public void processHaving(QuerySpecification querySpecNode) {
        final Optional<Expression> having = querySpecNode.getHaving();
        if (having.isPresent()) {
            final Expression havingValue = having.get();
            final AbstractQuerySpecHandler havingHandler = new HavingHandler(havingValue);
            havingHandler.process();
        }
    }

    public void processWhere(QuerySpecification querySpecNode) {
        final Optional<Expression> where = querySpecNode.getWhere();
        if (where.isPresent()) {
            final Expression whereExp = where.get();
            final AbstractQuerySpecHandler whereHandler = new WhereHandler(whereExp);
            whereHandler.process();

        }
    }

}
