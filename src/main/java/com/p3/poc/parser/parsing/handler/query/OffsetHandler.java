package com.p3.poc.parser.parsing.handler.query;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.others.OffsetInfo;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import com.p3.poc.parser.parsing.handler.expression.CommonExpressionHandler;
import io.trino.sql.tree.Offset;

public class OffsetHandler implements CommonQueryParser {

    private final QueryParsedDetails queryDetails;
    private final OffsetInfo offsetInfo;
    private final CommonExpressionHandler commonExpressionHandler;

    public OffsetHandler(QueryParsedDetails queryDetails) {
        this.queryDetails = queryDetails;
        this.offsetInfo= new OffsetInfo();
        this.commonExpressionHandler = new CommonExpressionHandler();
    }

    @Override
    public void processQuery(Object node) {
        final Offset offset = (Offset) node;
        final BaseExpressionInfo expression = commonExpressionHandler.handleExpression(offset.getRowCount());
        offsetInfo.setExpression(expression);
        queryDetails.setOffsetInfo(offsetInfo);
    }
}
