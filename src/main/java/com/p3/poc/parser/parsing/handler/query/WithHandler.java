package com.p3.poc.parser.parsing.handler.query;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.WithQueryObjectDetails;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;

public class WithHandler implements CommonQueryParser {
    private final QueryParsedDetails queryDetails;
    private WithQueryObjectDetails withQueryObjectDetails;

    public WithHandler(QueryParsedDetails queryDetails) {
        this.queryDetails = queryDetails;
        this.withQueryObjectDetails = new WithQueryObjectDetails();
    }


    @Override
    public void processQuery(Object node) {

    }
}
