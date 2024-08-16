package com.p3.poc.parser.parsing.handler.query.operations;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.parsing.handler.query.QueryProcessor;
import io.trino.sql.tree.Node;

public class OffsetHandler implements QueryProcessor {

    private final QueryParsedDetails queryDetails;

    public OffsetHandler(QueryParsedDetails queryDetails) {
        this.queryDetails = queryDetails;

    }

    @Override
    public void processQueryObject(Node node) {}
}
