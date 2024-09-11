package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.query_specification.service.SelectNodeHandler;
import io.trino.sql.tree.AllColumns;
import io.trino.sql.tree.Identifier;
import io.trino.sql.tree.Select;
import io.trino.sql.tree.SingleColumn;

import java.util.Optional;
import java.util.UUID;

public class SelectHandlerImpl implements SelectNodeHandler {

    private final ExpressionHandler commonExpressionHandler;

    public SelectHandlerImpl() {
        this.commonExpressionHandler = new ExpressionHandler();
    }

    @Override
    public void processSelectNode(Select selectNode) {
        GlobalCollector.getInstance().setDynamicSelectId("select:" + UUID.randomUUID());
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
        final Object obj = commonExpressionHandler.handleExpression(singleColumn.getExpression(), NodeType.SELECT, null);
        if(obj instanceof  ColumnDetails column){
            column.setColumnAliasName(alias.isPresent() ? String.valueOf(alias.get()) : "");
            commonExpressionHandler.saveColumnDetails(column, NodeType.SELECT);
        }
    }
}
