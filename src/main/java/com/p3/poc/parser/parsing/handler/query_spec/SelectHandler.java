package com.p3.poc.parser.parsing.handler.query_spec;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.SelectQueryInfo;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import io.trino.sql.tree.Select;
import lombok.Data;


@Data
public class SelectHandler implements CommonQueryParser {
    private QueryParsedDetails queryParsedDetails;
    private SelectQueryInfo selectInfo;
    public SelectHandler(QueryParsedDetails queryDetails) {
        this.queryParsedDetails = queryDetails;
        this.selectInfo = new SelectQueryInfo();
    }

    @Override
    public void processQuery(Object node) {
        final Select selectNode = (Select) node;

    }
}
