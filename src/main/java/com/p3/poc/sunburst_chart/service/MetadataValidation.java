package com.p3.poc.sunburst_chart.service;

import com.p3.poc.common.CommonColumProcessUtils;
import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.bean.parsing_details.SchemaDetails;
import com.p3.poc.parser.bean.parsing_details.TableDetails;
import com.p3.poc.sunburst_chart.SunBurstGlobalCollector;
import com.p3.poc.sunburst_chart.bean.metadata.ColumnMetadata;
import com.p3.poc.sunburst_chart.bean.metadata.ExportMetadata;
import com.p3.poc.sunburst_chart.bean.metadata.SchemaMetadata;
import com.p3.poc.sunburst_chart.bean.metadata.TableMetadata;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MetadataValidation {

    private final Map<String, List<ColumnDetails>> overAllColumnMap;
    private final Map<String, List<TableDetails>> overAllTableMap;
    private final Map<String, List<SchemaDetails>> overAllSchemaMap;

    private final ExportMetadata exportMetadata;
    private final CommonColumProcessUtils commonColumProcessUtils;

    public MetadataValidation(ExportMetadata exportMetadata) {
        this.commonColumProcessUtils = new CommonColumProcessUtils();
        final SunBurstGlobalCollector instance = SunBurstGlobalCollector.getInstance();
        this.exportMetadata = exportMetadata;
        this.overAllColumnMap = instance.getOverAllColumMap();
        this.overAllTableMap = instance.getOverAllTableMap();
        this.overAllSchemaMap = instance.getOverAllschemaMap();
    }

    public void processMetadata() {
        final List<SchemaMetadata> metadataSchema = exportMetadata.getMetadata().getSchemas();

        for (Map.Entry<String, List<SchemaDetails>> stringListEntry : overAllSchemaMap.entrySet()) {
            final List<SchemaDetails> schemaDetailsList = stringListEntry.getValue();
            Iterator<SchemaDetails> schemaIterator = schemaDetailsList.iterator();

            while (schemaIterator.hasNext()) {
                SchemaDetails schemaDetails = schemaIterator.next();
                final boolean anyMatch = metadataSchema.stream().anyMatch(schemaMetadata ->
                        schemaMetadata.getName().equalsIgnoreCase(schemaDetails.getSchemaName()));

                if (!anyMatch) {
                    schemaIterator.remove();
                } else {
                    final Optional<SchemaMetadata> first = metadataSchema.stream().filter(schemaMetadata ->
                            schemaMetadata.getName().equalsIgnoreCase(schemaDetails.getSchemaName())).findFirst();
                    first.ifPresent(schemaMetadata -> processTableValidation(schemaMetadata, schemaDetails.getId()));
                }
            }
        }
    }


    private void processTableValidation(SchemaMetadata schema, String id) {
        final List<TableMetadata> metadataTables = schema.getTables();
        final String tableMapKey = commonColumProcessUtils.getCaseInsensitiveMapKey(overAllTableMap, id);
        final List<TableDetails> tableDetailsList = overAllTableMap.get(tableMapKey);
        Iterator<TableDetails> tableIterator = tableDetailsList.iterator();

        while (tableIterator.hasNext()) {
            TableDetails tableDetails = tableIterator.next();
            final boolean anyMatch = metadataTables.stream().anyMatch(tableMetadata -> tableMetadata.getName()
                    .equalsIgnoreCase(tableDetails.getName()));

            if (!anyMatch) {
                tableIterator.remove();
            } else {
                final Optional<TableMetadata> first = metadataTables.stream().filter(tableMetadata -> tableMetadata.getName()
                        .equalsIgnoreCase(tableDetails.getName())).findFirst();
                first.ifPresent(aBoolean -> processColumnValidations(first.get(), tableDetails.getAliasName()));
            }
        }
    }

    private void processColumnValidations(TableMetadata tableMetadata, String aliasName) {
        String key = aliasName.isEmpty() ? "DEFAULT_TABLE" : aliasName;
        final String columnMapKey = commonColumProcessUtils.getCaseInsensitiveMapKey(overAllColumnMap, key);
        final List<ColumnDetails> columnDetailsList = overAllColumnMap.get(columnMapKey);
        final List<ColumnMetadata> columnMetadataList = tableMetadata.getColumns();
        Iterator<ColumnDetails> columnIterator = columnDetailsList.iterator();

        while (columnIterator.hasNext()) {
            ColumnDetails columnDetails = columnIterator.next();
            final boolean anyMatch = columnMetadataList.stream().anyMatch(columnMetadata -> columnMetadata.getName()
                    .equalsIgnoreCase(columnDetails.getColumnName()));

            if (!anyMatch) {
                columnIterator.remove();
            }
        }
    }





}
