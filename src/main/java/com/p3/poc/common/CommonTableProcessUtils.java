package com.p3.poc.common;

import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.bean.parsing_details.ApplicationDetails;
import com.p3.poc.parser.bean.parsing_details.SchemaDetails;
import com.p3.poc.parser.bean.parsing_details.TableDetails;
import com.p3.poc.sunburst_chart.SunBurstGlobalCollector;
import io.trino.sql.tree.Identifier;
import io.trino.sql.tree.QualifiedName;
import io.trino.sql.tree.Table;

import java.util.*;

public class CommonTableProcessUtils {

    private final GlobalCollector instance;
    private final SunBurstGlobalCollector sunBurstGlobalCollector;
    private final boolean isChartView;

    public CommonTableProcessUtils(boolean isChartView) {
        this.isChartView = isChartView;
        this.sunBurstGlobalCollector = SunBurstGlobalCollector.getInstance();
        this.instance = GlobalCollector.getInstance();
    }
    public void processTableDetails(Table tableRelation, TableDetails tableDetails) {
        tableDetails.setId(generateTableId());
        tableDetails.setFullName(tableRelation.getName().toString());

        String[] nameParts = extractApplicationAndSchema(tableRelation.getName());
        String applicationName = nameParts[0];
        String schemaName = nameParts[1];
        String tableName = nameParts[2];

        tableDetails.setName(tableName);

        ApplicationDetails applicationDetails = processApplication(applicationName);
        SchemaDetails schemaDetails = processSchema(applicationDetails.getId(), schemaName);

        // verification purpose schema name put in table details
        tableDetails.setSchemaName(schemaName);
        processTable(schemaDetails.getId(), tableDetails);
    }

    private String generateTableId() {
        return UUID.randomUUID().toString();
    }

    private String[] extractApplicationAndSchema(QualifiedName name) {
        List<Identifier> originalParts = name.getOriginalParts();
        String applicationSchemaName = "";
        String tableName = "";

        if (originalParts.size() == 2) {
            applicationSchemaName = String.valueOf(originalParts.get(0));
            tableName = String.valueOf(originalParts.get(1));
        } else if (originalParts.size() == 1) {
            tableName = String.valueOf(originalParts.get(0));
        }

        String[] parts = applicationSchemaName.split("_");
        String schemaName = parts[parts.length - 1];
        String applicationName = String.join("_", Arrays.copyOfRange(parts, 0, parts.length - 1));

        return new String[]{applicationName, schemaName, tableName};
    }

    private ApplicationDetails processApplication(String applicationName) {
        if (isChartView) {
            return getApplicationDetails(applicationName, sunBurstGlobalCollector.getOverAllApplicationMap(), sunBurstGlobalCollector.getSearchId());
        }
        return getApplicationDetails(applicationName, instance.getOverAllApplicationMap(), instance.getSearchId());
    }

    private ApplicationDetails getApplicationDetails(String applicationName, Map<String, List<ApplicationDetails>> overAllApplicationMap, String searchId) {
        final List<ApplicationDetails> applicationDetails = overAllApplicationMap.computeIfAbsent(searchId, key -> new ArrayList<>());
        return applicationDetails.stream()
                .filter(detail -> detail.getApplicationName().equals(applicationName))
                .findFirst()
                .orElseGet(() -> {
                    final ApplicationDetails details = ApplicationDetails.builder()
                            .id(UUID.randomUUID().toString())
                            .applicationName(applicationName)
                            .build();
                    applicationDetails.add(details);
                    return details;
                });
    }

    private SchemaDetails processSchema(String applicationId, String schemaName) {
        if (isChartView) {
            return getSchemaDetails(applicationId, schemaName, sunBurstGlobalCollector.getOverAllschemaMap());
        }
        return getSchemaDetails(applicationId, schemaName, instance.getOverAllschemaMap());
    }

    private SchemaDetails getSchemaDetails(String applicationId, String schemaName, Map<String, List<SchemaDetails>> overAllschemaMap) {
        List<SchemaDetails> schemaDetailsList = overAllschemaMap.computeIfAbsent(applicationId, k -> new ArrayList<>());
        return schemaDetailsList.stream()
                .filter(schema -> schema.getSchemaName().equals(schemaName))
                .findFirst()
                .orElseGet(() -> {
                    SchemaDetails schemaDetails = SchemaDetails.builder()
                            .id(UUID.randomUUID().toString())
                            .schemaName(schemaName)
                            .build();
                    schemaDetailsList.add(schemaDetails);
                    return schemaDetails;
                });
    }

    private void processTable(String schemaId, TableDetails tableDetails) {
        Map<String, List<TableDetails>> tableMap;
        if (isChartView) {
            tableMap = sunBurstGlobalCollector.getOverAllTableMap();
        }else {
            tableMap = instance.getOverAllTableMap();
        }
        tableMap.computeIfAbsent(schemaId, k -> new ArrayList<>()).add(tableDetails);
    }

}
