package com.p3.poc.lineage.sunburst_chart.utils;

import com.p3.poc.lineage.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.lineage.sunburst_chart.SunBurstGlobalCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class commonUtils {
    private final Map<String, List<ColumnDetails>> overallColumnMap;

    public commonUtils() {
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

    public <T> String getCaseInsensitiveMapKey(Map<String, List<T>> stringListMap, String key) {
        String caseInsensitiveKey = key;
        for (String mapKey : stringListMap.keySet()) {
            if (mapKey.equalsIgnoreCase(key)) {
                caseInsensitiveKey = mapKey;
                break;
            }
        }
        return caseInsensitiveKey;
    }

}
