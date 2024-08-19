package com.p3.poc.parser.parsing.handler.query;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;

public class LimitHandler implements CommonQueryParser {
    private final QueryParsedDetails queryDetails;

    public LimitHandler(QueryParsedDetails queryDetails) {
        this.queryDetails = queryDetails;
    }


    @Override
    public void processQuery(Object node) {

    }
}
