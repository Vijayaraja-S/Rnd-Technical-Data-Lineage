package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.parser.bean.parsing_details.OffsetInfo;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import io.trino.sql.tree.Offset;

import java.util.Map;

public class OffsetHandler extends AbstractQuerySpecHandler {
    private final ExpressionHandler expressionHandler;
    private final Offset offset;

    public OffsetHandler(Offset node) {
        this.offset = node;
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public void process() {
        final OffsetInfo offsetInfo = OffsetInfo.builder().build();
        expressionHandler.handleExpression(offset.getRowCount(), NodeType.OFFSET, offsetInfo);
        final GlobalCollector instance = GlobalCollector.getInstance();
        final Map<String, OffsetInfo> offsetInfoMap = instance.getOffsetInfoMap();
        offsetInfoMap.put(instance.getDynamicSelectId(), offsetInfo);
    }
}
