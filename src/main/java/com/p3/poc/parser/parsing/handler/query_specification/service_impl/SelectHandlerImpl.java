package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.query_specification.service.SelectNodeHandler;
import io.trino.sql.tree.*;

import java.util.*;

public class SelectHandlerImpl implements SelectNodeHandler {

    private final ExpressionHandler commonExpressionHandler;

    public SelectHandlerImpl() {
        this.commonExpressionHandler = new ExpressionHandler();
    }

    @Override
    public void processSelectNode(Select selectNode) {
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

        final ColumnDetails column = ColumnDetails.builder()
                .columnAliasName(alias.isPresent() ? String.valueOf(alias.get()) : "")
                .build();
        commonExpressionHandler.handleExpression(singleColumn.getExpression(), column);

        Map<String, List<ColumnDetails>> columnListMap = GlobalCollector.getInstance().getColumnListMap();
        List<ColumnDetails> columnList = columnListMap.computeIfAbsent(column.getColumnSource(), k -> new ArrayList<>());
        int index = columnList.size();
        column.setColumnId(column.getColumnSource() + ":c" + index);
        columnList.add(column);
    }
}
