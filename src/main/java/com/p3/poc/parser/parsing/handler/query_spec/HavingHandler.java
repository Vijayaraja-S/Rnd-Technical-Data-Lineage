package com.p3.poc.parser.parsing.handler.query_spec;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.others.HavingQueryInfo;
import com.p3.poc.parser.parsing.handler.expression.CommonExpressionHandler;
import io.trino.sql.tree.Expression;
import lombok.Data;

@Data
public class HavingHandler {
    private  QueryParsedDetails queryParsedDetails;
    private CommonExpressionHandler commonExpressionHandler;
    public HavingHandler(QueryParsedDetails queryParsedDetails) {
        this.queryParsedDetails = queryParsedDetails;
        this.commonExpressionHandler = new CommonExpressionHandler();
    }

    public void processNode(Expression havingExpression) {
        final BaseExpressionInfo baseExpressionInfo = commonExpressionHandler.handleExpression(havingExpression);
        queryParsedDetails.setHavingQueryInfo(HavingQueryInfo
                .builder()
                .queryExpressionInfo(baseExpressionInfo)
                .build());
    }
}
