package com.p3.poc.parser.parsing.handler.query_body;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import com.p3.poc.parser.parsing.handler.query_spec.FromHandler;
import com.p3.poc.parser.parsing.handler.query_spec.HavingHandler;
import com.p3.poc.parser.parsing.handler.query_spec.WhereHandler;
import com.p3.poc.parser.parsing.utils.HandlerChecker;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.QuerySpecification;
import io.trino.sql.tree.Relation;

import java.util.Optional;

public class QuerySpecificationHandler implements CommonQueryParser {
    private final QueryParsedDetails queryParsedDetails;
    public QuerySpecificationHandler(QueryParsedDetails queryDetails) {
        this.queryParsedDetails = queryDetails;
    }

    @Override
    public void processQuery(Object node) {
        final QuerySpecification querySpecNode = (QuerySpecification) node;

        handleFromNode(querySpecNode);
        handleWhereAnsHaving(querySpecNode);

        var children = querySpecNode.getChildren();
        if (!children.isEmpty()){
            children
                    .forEach(child -> {
                        if (!(child instanceof Expression) && !(child instanceof Relation)) {
                            CommonQueryParser handler = HandlerChecker.getHandler(child);
                            handler.processQuery(child);
                        }
                    });
        }
    }

    private void handleFromNode(QuerySpecification querySpecNode) {
        final Optional<Relation> from = querySpecNode.getFrom();
        if (from.isPresent()){
            final Relation relation = from.get();
            final FromHandler fromHandler = new FromHandler(queryParsedDetails);
            fromHandler.processNode(relation);
        }
    }

    private  void handleWhereAnsHaving(QuerySpecification querySpecNode) {
        final Optional<Expression> having = querySpecNode.getHaving();
        if (having.isPresent()) {
            final Expression havingValue = having.get();
            new HavingHandler(queryParsedDetails).processNode(havingValue);
        }
        final Optional<Expression> where = querySpecNode.getWhere();
        if (where.isPresent()) {
            final Expression whereValue = where.get();
            new WhereHandler(queryParsedDetails).processNode(whereValue);
        }
    }
}
