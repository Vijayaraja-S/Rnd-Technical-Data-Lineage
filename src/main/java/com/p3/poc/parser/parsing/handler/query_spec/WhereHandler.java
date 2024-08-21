package com.p3.poc.parser.parsing.handler.query_spec;

import com.p3.poc.parser.bean.QueryParsedDetails;
import io.trino.sql.tree.Expression;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WhereHandler {
    private QueryParsedDetails queryParsedDetails;
    public WhereHandler(QueryParsedDetails queryParsedDetails) {
        this.queryParsedDetails = queryParsedDetails;
    }
    public void processNode(Expression whereExpression) {

    }
}
