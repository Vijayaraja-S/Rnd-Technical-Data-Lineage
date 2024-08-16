package com.p3.poc.parser.parsing.handler.query.operations;


import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.parsing.handler.query.QueryProcessor;
import io.trino.sql.tree.Node;

public class QueryBodyHandler implements QueryProcessor {
    private final QueryParsedDetails queryDetails;

    public QueryBodyHandler(QueryParsedDetails queryDetails) {
        this.queryDetails = queryDetails;
    }

    public void processQueryObject(Node node) {
        System.out.println(node.toString());
    }
}
