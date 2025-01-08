package com.p3.poc.lineage.sunburst_chart.service;

import com.p3.poc.lineage.common.CommonTableProcessUtils;
import com.p3.poc.lineage.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.lineage.parser.bean.parsing_details.TableDetails;
import com.p3.poc.lineage.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.lineage.parser.parsing.handler.expression.utils.ExpressionUtils;
import com.p3.poc.lineage.sunburst_chart.utils.commonUtils;
import io.trino.sql.tree.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Optional;

public class ProcessQuery {

    private ColumnDetails columnDetails = new ColumnDetails();
    private TableDetails tableDetails = new TableDetails();
    private final ExpressionUtils expressionUtils;
    private final commonUtils commonColumProcessUtils;
    private final CommonTableProcessUtils commonTableProcessUtils;

    public ProcessQuery() {
        this.expressionUtils = new ExpressionUtils(NodeType.NONE);
        this.commonColumProcessUtils = new commonUtils();
        this.commonTableProcessUtils = new CommonTableProcessUtils(true);
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
        } else if (child instanceof Table table) {
            processTableRelation(table);
        } else if (child instanceof SingleColumn column) {
            processSingleColumn(column);
        } else if (child instanceof AllColumns allColumns) {
            //
        } else {
            processQueryObject(child);
        }
    }

    private void processSingleColumn(SingleColumn singleColumn) {
        final Optional<Identifier> alias = singleColumn.getAlias();
        final ColumnDetails column = ColumnDetails.builder().build();
        this.columnDetails = column;
        column.setColumnAliasName(alias.isPresent() ? String.valueOf(alias.get()) : "");
        if (singleColumn.getExpression() instanceof DereferenceExpression dereferenceExpression) {
            saveColumDetails(dereferenceExpression);
        } else {
            checkNode(singleColumn.getExpression());
        }
    }

    private void processIdentifier(Identifier identifier) {
        this.columnDetails = ColumnDetails.builder().build();
        this.columnDetails.setColumnName(identifier.getValue());
        commonColumProcessUtils.saveColumnDetails(columnDetails);
    }

    private void processDereference(DereferenceExpression expression) {
        this.columnDetails = ColumnDetails.builder().build();
        saveColumDetails(expression);
    }

    private void processTableRelation(Table table) {
        this.tableDetails = TableDetails.builder().build();
        saveTableDetails(table);
    }

    private void processAliasRelationShip(AliasedRelation aliasedRelation) {
        this.tableDetails = TableDetails.builder().build();
        tableDetails.setAliasName(String.valueOf(aliasedRelation.getAlias()));
        //Handled Table Details with alias
        if (aliasedRelation.getRelation() instanceof Table table) {
            saveTableDetails(table);
        }
        //handle alias columns
        if (!CollectionUtils.isEmpty(aliasedRelation.getColumnNames())) {
            aliasedRelation.getColumnNames().forEach(this::checkNode);
        }
        // handled other nodes
        final List<Node> children = aliasedRelation.getChildren();
        if (!children.isEmpty()) {
            children.forEach(this::checkNode);
        }
    }

    private void saveTableDetails(Table table) {
        commonTableProcessUtils.processTableDetails(table, tableDetails);
    }


    private void saveColumDetails(DereferenceExpression dereferenceExpression) {
        expressionUtils.processColumnDetails(dereferenceExpression, columnDetails);
        commonColumProcessUtils.saveColumnDetails(columnDetails);
    }
}
