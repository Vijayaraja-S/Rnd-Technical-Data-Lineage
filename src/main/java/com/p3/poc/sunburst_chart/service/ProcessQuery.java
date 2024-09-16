package com.p3.poc.sunburst_chart.service;

import com.p3.poc.common.CommonColumProcessUtils;
import com.p3.poc.common.CommonTableProcessUtils;
import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.bean.parsing_details.TableDetails;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.utils.ExpressionUtils;
import io.trino.sql.tree.*;

import java.util.List;
import java.util.Optional;

public class ProcessQuery {

    private ColumnDetails columnDetails = new ColumnDetails();
    private TableDetails tableDetails=new TableDetails();
    private final ExpressionUtils expressionUtils;
    private final CommonColumProcessUtils commonColumProcessUtils;
    private final CommonTableProcessUtils commonTableProcessUtils;

    public ProcessQuery() {
        this.expressionUtils = new ExpressionUtils(NodeType.NONE);
        this.commonColumProcessUtils = new CommonColumProcessUtils();
        this.commonTableProcessUtils=new CommonTableProcessUtils(true);
    }

    public void processQueryObject(Node query) {
        final List<? extends Node> children = query.getChildren();
        for (Node child : children) {
            checkNode(child);
        }
    }

    private void checkNode(Node child) {
        if (child instanceof DereferenceExpression expression) {
            processDereference(expression);
        } else if (child instanceof Identifier identifier) {
            processIdentifier(identifier);
        } else if (child instanceof AliasedRelation aliasedRelation) {
            processAliasRelationShip(aliasedRelation);
        } else if (child instanceof Table table ) {
            processTableRelation(table);
        } else if (child instanceof SingleColumn column) {
            processSingleColumn(column);
        }  else if (child instanceof AllColumns allColumns) {
            //
        }else {
            processQueryObject(child);
        }
    }

    private void processSingleColumn(SingleColumn singleColumn) {
        final Optional<Identifier> alias = singleColumn.getAlias();
        final ColumnDetails column = ColumnDetails.builder().build();
        columnDetails = column;
        column.setColumnAliasName(alias.isPresent() ? String.valueOf(alias.get()) : "");
        checkNode(singleColumn.getExpression());
    }

    private void processIdentifier(Identifier identifier) {
        columnDetails = ColumnDetails.builder().build();
        columnDetails.setColumnName(identifier.getValue());
        commonColumProcessUtils.saveColumnDetails(columnDetails);
    }

    private void processDereference(DereferenceExpression expression) {
        expressionUtils.processColumnDetails(expression, columnDetails);
        commonColumProcessUtils.saveColumnDetails(columnDetails);
    }

    private void processTableRelation(Table table) {
        commonTableProcessUtils.processTableDetails(table, tableDetails);
    }

    private void processAliasRelationShip(AliasedRelation aliasedRelation) {
        tableDetails=TableDetails.builder().build();
        tableDetails.setAliasName(String.valueOf(aliasedRelation.getAlias()));
        checkNode(aliasedRelation.getRelation());
    }

}
