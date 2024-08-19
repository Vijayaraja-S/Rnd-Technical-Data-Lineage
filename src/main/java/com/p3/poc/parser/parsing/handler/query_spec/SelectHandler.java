package com.p3.poc.parser.parsing.handler.query_spec;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.SelectQueryInfo;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import lombok.Data;


@Data
public class SelectHandler implements CommonQueryParser {
    private QueryParsedDetails queryParsedDetails;
    private SelectQueryInfo selectInfo;
    public SelectHandler(QueryParsedDetails queryDetails) {
        this.queryParsedDetails = queryDetails;
    }

    @Override
    public void processQuery(Object node) {

    }
}
