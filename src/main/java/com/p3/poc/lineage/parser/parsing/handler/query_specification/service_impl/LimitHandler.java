package com.p3.poc.lineage.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.lineage.parser.bean.parsing_details.LimitInfo;
import com.p3.poc.lineage.parser.bean.GlobalCollector;
import com.p3.poc.lineage.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.lineage.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.lineage.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import io.trino.sql.tree.Limit;

import java.util.Map;

public class LimitHandler extends AbstractQuerySpecHandler {
    private final ExpressionHandler expressionHandler;
    private final Limit limit;

    public LimitHandler(Limit node) {
        this.limit =  node;
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public void process() {
        final LimitInfo limitInfo = LimitInfo.builder().build();
        expressionHandler.handleExpression(limit.getRowCount(), NodeType.LIMIT, limitInfo);
        final GlobalCollector instance = GlobalCollector.getInstance();
        final Map<String, LimitInfo> limitInfoMap = instance.getLimitInfoMap();
        limitInfoMap.put(instance.getDynamicSelectId(), limitInfo);
    }
}
