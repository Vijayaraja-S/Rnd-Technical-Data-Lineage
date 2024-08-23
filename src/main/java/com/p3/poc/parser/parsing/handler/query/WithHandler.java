package com.p3.poc.parser.parsing.handler.query;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.with.WithObjectInfo;
import com.p3.poc.parser.bean.with.WithQueryObjectDetails;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import io.trino.sql.tree.With;
import io.trino.sql.tree.WithQuery;
import lombok.Data;

import java.util.List;


@Data
public class WithHandler implements CommonQueryParser {
    private final QueryParsedDetails queryDetails;
    private WithQueryObjectDetails withQueryObjectDetails;
    private WithObjectInfo initialWithObjectBean;

    public WithHandler(QueryParsedDetails queryDetails) {
        this.queryDetails = queryDetails;
        this.withQueryObjectDetails = new WithQueryObjectDetails();
    }


    @Override
    public void processQuery(Object node) {

    }
}
