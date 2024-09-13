package com.p3.poc.chart;

import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.bean.parsing_details.*;
import org.json.JSONArray;
import org.json.JSONObject;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SunburstChartHelper {

    private final Map<String, List<ColumnDetails>> overAllColumnMap;
    private final Map<String, List<TableDetails>> overAllTableMap;
    private final Map<String, List<SchemaDetails>> overAllSchemaMap;
    private final Map<String, List<ApplicationDetails>> overAllApplicationMap;
    private final GlobalCollector globalCollector;

    public SunburstChartHelper() {
        final GlobalCollector instance = GlobalCollector.getInstance();
        this.globalCollector = instance;
        this.overAllColumnMap = instance.getOverAllColumMap();
        this.overAllTableMap = instance.getOverAllTableMap();
        this.overAllSchemaMap = instance.getOverAllschemaMap();
        this.overAllApplicationMap = instance.getOverAllApplicationMap();
    }


    public JSONArray generateSunburst() {
        JSONArray data = new JSONArray();
        SunburstNode root = new SunburstNode(globalCollector.getSearchId(), "", globalCollector.getSearchName(), 0);
        data.put(root.toJson());

        List<JSONObject> applicationNodes = overAllApplicationMap.entrySet()
                .parallelStream()
                .map(this::createApplicationNodeWithSchemas)
                .flatMap(List::stream)
                .toList();

        applicationNodes.forEach(data::put);
        return data;
    }


    private List<JSONObject> createApplicationNodeWithSchemas(Map.Entry<String, List<ApplicationDetails>> appEntry) {
        List<ApplicationDetails> appDetailsList = appEntry.getValue();

        return appDetailsList.stream()
                .map(appDetails -> {
                    SunburstNode appNode = new SunburstNode(appDetails.getId(), globalCollector.getSearchId(), appDetails.getApplicationName(), 0);

                    List<JSONObject> schemaNodes = overAllSchemaMap.getOrDefault(appDetails.getId(), List.of())
                            .parallelStream()
                            .map(schema -> createSchemaNodeWithTables(appNode.getId(), schema))
                            .flatMap(List::stream)
                            .collect(Collectors.toList());

                    schemaNodes.add(0, appNode.toJson());
                    return schemaNodes;
                })
                .flatMap(List::stream)
                .toList();
    }


    private List<JSONObject> createSchemaNodeWithTables(String appId, SchemaDetails schemaDetails) {
        SunburstNode schemaNode = new SunburstNode(schemaDetails.getId(), appId, schemaDetails.getSchemaName(), 0);

        List<JSONObject> tableNodes = overAllTableMap.getOrDefault(schemaDetails.getId(), List.of())
                .parallelStream()
                .map(table -> createTableNodeWithColumns(schemaNode.getId(), table))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        tableNodes.add(0, schemaNode.toJson());
        return tableNodes;
    }


    private List<JSONObject> createTableNodeWithColumns(String schemaId, TableDetails tableDetails) {
        SunburstNode tableNode = new SunburstNode(tableDetails.getAliasName(), schemaId, tableDetails.getName(), 0);

        List<JSONObject> columnNodes = overAllColumnMap.getOrDefault(tableDetails.getAliasName(), List.of())
                .parallelStream()
                .map(column -> createColumnNode(tableNode.getId(), column))
                .collect(Collectors.toList());

        columnNodes.add(0, tableNode.toJson());
        return columnNodes;
    }

    private JSONObject createColumnNode(String tableId, ColumnDetails column) {

        SunburstNode columnNode = new SunburstNode(
                column.getId(),
                tableId,
                column.getColumnName(),1
        );

        return columnNode.toJson();
    }
}
