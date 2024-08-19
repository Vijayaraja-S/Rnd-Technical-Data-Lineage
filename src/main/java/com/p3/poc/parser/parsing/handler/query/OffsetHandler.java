package com.p3.poc.parser.parsing.handler.query;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;

public class OffsetHandler implements CommonQueryParser {

    private final QueryParsedDetails queryDetails;

    public OffsetHandler(QueryParsedDetails queryDetails) {
        this.queryDetails = queryDetails;

    }

    @Override
    public void processQuery(Object node) {

    }
}
