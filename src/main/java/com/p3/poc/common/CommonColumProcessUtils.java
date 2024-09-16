package com.p3.poc.common;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.sunburst_chart.SunBurstGlobalCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonColumProcessUtils {
    private final Map<String, List<ColumnDetails>> overallColumnMap;

    public CommonColumProcessUtils() {
        this.overallColumnMap = SunBurstGlobalCollector.getInstance().getOverAllColumMap();
    }

    public void saveColumnDetails(ColumnDetails column) {
        if (overallColumnMap.containsKey(column.getColumnSource())) {
            final List<ColumnDetails> columnDetails = overallColumnMap.get(column.getColumnSource());
            if (columnDetails.stream()
                    .noneMatch(col -> col.getColumnName().equalsIgnoreCase(column.getColumnName()))) {
                List<ColumnDetails> columnList = overallColumnMap.computeIfAbsent(column.getColumnSource(), k -> new ArrayList<>());
                setColumId(column, columnList);
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

}
