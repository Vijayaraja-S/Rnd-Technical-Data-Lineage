package com.p3.poc.parser.parsing.handler.query_body;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import io.trino.sql.tree.Node;
import lombok.Data;

@Data
public class ExceptHandler implements CommonQueryParser {
    private QueryParsedDetails queryParsedDetails;

    public ExceptHandler(QueryParsedDetails queryDetails) {
        this.queryParsedDetails = queryDetails;
    }

    @Override
    public void processQuery(Object node) {

    }
}
