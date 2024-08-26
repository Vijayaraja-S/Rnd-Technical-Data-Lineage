package com.p3.poc.parser.parsing.handler.query;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.others.LimitInfo;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import com.p3.poc.parser.parsing.handler.expression.CommonExpressionHandler;
import io.trino.sql.tree.Limit;

public class LimitHandler implements CommonQueryParser {
    private final QueryParsedDetails queryDetails;
    private final LimitInfo limitInfo;
    private final CommonExpressionHandler commonExpressionHandler;

    public LimitHandler(QueryParsedDetails queryDetails) {
        this.queryDetails = queryDetails;
        this.limitInfo = new LimitInfo();
        commonExpressionHandler = new CommonExpressionHandler();
    }


    @Override
    public void processQuery(Object node) {
        final Limit limit = (Limit) node;
        final BaseExpressionInfo expression = commonExpressionHandler.handleExpression(limit.getRowCount());
        limitInfo.setExpression(expression);
        queryDetails.setLimitInfo(limitInfo);
    }
}
