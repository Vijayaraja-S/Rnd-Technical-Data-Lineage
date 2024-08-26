package com.p3.poc.parser.parsing.handler.query_spec;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.others.WhereQueryInfo;
import com.p3.poc.parser.parsing.handler.expression.CommonExpressionHandler;
import io.trino.sql.tree.Expression;
import lombok.Data;

@Data
public class WhereHandler {
    private QueryParsedDetails queryParsedDetails;
    private CommonExpressionHandler commonExpressionHandler;
    public WhereHandler(QueryParsedDetails queryParsedDetails) {
        this.queryParsedDetails = queryParsedDetails;
        this.commonExpressionHandler = new CommonExpressionHandler();
    }
    public void processNode(Expression whereExpression) {
        final BaseExpressionInfo baseExpressionInfo = commonExpressionHandler.handleExpression(whereExpression);
        queryParsedDetails.setWhereQueryInfo(WhereQueryInfo
                .builder()
                .queryExpressionInfo(baseExpressionInfo)
                .build());
    }

}
