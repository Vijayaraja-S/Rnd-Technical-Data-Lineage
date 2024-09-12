package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import io.trino.sql.tree.*;

import java.util.Optional;
import java.util.UUID;

public class SelectHandler extends AbstractQuerySpecHandler {

    private final ExpressionHandler commonExpressionHandler;
    private final Select selectNode;

    public SelectHandler(Select node) {
        this.selectNode = node;
        this.commonExpressionHandler = new ExpressionHandler();
    }

    @Override
    public void process() {
        final GlobalCollector instance = GlobalCollector.getInstance();
        instance.setDynamicSelectId("select:" + UUID.randomUUID());
        selectNode.getSelectItems().
                forEach(selectItem -> {
                    if (selectItem instanceof SingleColumn singleColumn) {
                        processSingleColumn(singleColumn);
                    } else if (selectItem instanceof AllColumns allColumns) {
                        //
                    } else {
                        throw new IllegalArgumentException("Unsupported select item: " + selectItem);
                    }
                });

    }

    void processSingleColumn(SingleColumn singleColumn) {
        final Optional<Identifier> alias = singleColumn.getAlias();
        final ColumnDetails column = ColumnDetails.builder().build();
        column.setColumnAliasName(alias.isPresent() ? String.valueOf(alias.get()) : "");
        commonExpressionHandler.handleExpression(singleColumn.getExpression(), NodeType.SELECT, column);
        commonExpressionHandler.saveColumnDetails(column, NodeType.SELECT);

    }
}
