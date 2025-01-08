package com.p3.poc.lineage.parser.parsing.handler.expression.utils;

import com.p3.poc.lineage.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.lineage.parser.bean.GlobalCollector;
import com.p3.poc.lineage.parser.parsing.handler.expression.bean.indentifier.NodeType;
import io.trino.sql.tree.DereferenceExpression;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.Identifier;

import java.util.*;

public class ExpressionUtils {
    private final Map<String, List<ColumnDetails>> overallColumnMap;
    private NodeType nodeType;

    public ExpressionUtils(NodeType nodeType) {
        this.nodeType = nodeType;
        this.overallColumnMap = GlobalCollector.getInstance().getOverAllColumMap();
    }

    public void saveColumnDetails(ColumnDetails column) {
        if (overallColumnMap.containsKey(column.getColumnSource())) {
            final List<ColumnDetails> columnDetails = overallColumnMap.get(column.getColumnSource());
            if (columnDetails.stream()
                    .noneMatch(col -> col.getColumnName().equalsIgnoreCase(column.getColumnName()))) {
                List<ColumnDetails> columnList = overallColumnMap.computeIfAbsent(column.getColumnSource(), k -> new ArrayList<>());
                setColumId(column, columnList);
            }else {

                final Optional<ColumnDetails> first = columnDetails.stream()
                        .filter(col -> col.getColumnName().equalsIgnoreCase(column.getColumnName()))
                        .findFirst();

                if (first.isPresent()) {
                    column.setColumnSource(first.get().getColumnSource());
                    column.setColumnName(first.get().getColumnName());
                    column.setId(first.get().getId());
                }
            }
        } else {
            final List<ColumnDetails> value = new ArrayList<>();
            setColumId(column, value);
            overallColumnMap.put(column.getColumnSource(), value);
        }
    }

    private void setColumId(ColumnDetails column, List<ColumnDetails> columnList) {
        int index = columnList.size();
        column.setId(column.getColumnSource() + ":c" + index);
        columnList.add(column);
    }


    public void processColumnDetails(DereferenceExpression expression, ColumnDetails columnDetails) {
//        final Optional<Identifier> field = expression.getField();
//        final Expression base = expression.getBase();
//
//        if (base instanceof Identifier identifier) {
//            columnDetails.setColumnSource(identifier.getValue());
//        }
//        columnDetails.setColumnName(field.isPresent() ? field.get().toString() : "");
    }
}
