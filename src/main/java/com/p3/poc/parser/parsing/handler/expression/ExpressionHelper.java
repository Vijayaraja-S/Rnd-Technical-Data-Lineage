package com.p3.poc.parser.parsing.handler.expression;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.parser.bean.GlobalCollector;
import io.trino.sql.tree.DereferenceExpression;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExpressionHelper {


    public ColumnDetails saveColumnDetails(ColumnDetails column, ExpressionType type) {
        Map<String, List<ColumnDetails>> overallColumnMap = GlobalCollector.getInstance().getOverallColumnMap();

        if (overallColumnMap.containsKey(column.getColumnSource())) {
            final List<ColumnDetails> columnDetails = overallColumnMap.get(column.getColumnSource());
            if (columnDetails.stream()
                    .noneMatch(col -> col.getColumnName().equalsIgnoreCase(column.getColumnName()))) {
                List<ColumnDetails> columnList = overallColumnMap.computeIfAbsent(column.getColumnSource(), k -> new ArrayList<>());
                setColumId(column, columnList);
            }
        }else {
            final List<ColumnDetails> value = new ArrayList<>();
            setColumId(column, value);
            overallColumnMap.put(column.getColumnSource(), value);
        }
        return column;
    }

    private void setColumId(ColumnDetails column, List<ColumnDetails> columnList) {
        int index = columnList.size();
        column.setColumnId(column.getColumnSource() + ":c" + index);
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
