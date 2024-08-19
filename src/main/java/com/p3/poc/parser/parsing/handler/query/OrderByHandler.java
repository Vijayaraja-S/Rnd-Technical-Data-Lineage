package com.p3.poc.parser.parsing.handler.query;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;

public class OrderByHandler implements CommonQueryParser {
    private final QueryParsedDetails queryDetails;

    public OrderByHandler(QueryParsedDetails queryDetails) {
        this.queryDetails = queryDetails;
    }


    @Override
    public void processQuery(Object node) {

    }
}
