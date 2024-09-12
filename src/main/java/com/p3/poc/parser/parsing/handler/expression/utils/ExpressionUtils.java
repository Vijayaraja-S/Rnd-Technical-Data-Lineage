package com.p3.poc.parser.parsing.handler.expression.utils;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import io.trino.sql.tree.DereferenceExpression;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExpressionUtils {
    public ColumnDetails saveColumnDetails(ColumnDetails column, NodeType type) {
        Map<String, List<ColumnDetails>> overallColumnMap = GlobalCollector.getInstance().getOverAllColumMap();

        if (overallColumnMap.containsKey(column.getColumnSource())) {
            final List<ColumnDetails> columnDetails = overallColumnMap.get(column.getColumnSource());
            if (columnDetails.stream()
                    .noneMatch(col -> col.getColumnName().equalsIgnoreCase(column.getColumnName()))) {
                List<ColumnDetails> columnList = overallColumnMap.computeIfAbsent(column.getColumnSource(), k -> new ArrayList<>());
                setColumId(column, columnList);
                return column;
            }else{
                final Optional<ColumnDetails> first = columnDetails.stream()
                        .filter(col -> col.getColumnName().equalsIgnoreCase(column.getColumnName()))
                        .findFirst();
                if (first.isPresent()) {
                    return first.get();
                }
            }
        }else {
            final List<ColumnDetails> value = new ArrayList<>();
            setColumId(column, value);
            overallColumnMap.put(column.getColumnSource(), value);
            return column;
        }
        return null;
    }

    private void setColumId(ColumnDetails column, List<ColumnDetails> columnList) {
        int index = columnList.size();
        column.setId(column.getColumnSource() + ":c" + index);
        columnList.add(column);
    }


    public ColumnDetails getColumnDetails(DereferenceExpression expression) {
        final ColumnDetails columnDetails = ColumnDetails.builder().build();
        final Optional<Identifier> field = expression.getField();
        final Expression base = expression.getBase();

        if (base instanceof Identifier identifier) {
            columnDetails.setColumnSource(identifier.getValue());
        }
        columnDetails.setColumnName(field.isPresent() ? field.get().toString() : "");
        return columnDetails;
    }
}
