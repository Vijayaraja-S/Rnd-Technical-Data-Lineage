package com.p3.poc.sunburst_chart.service;

import com.google.gson.Gson;
import com.p3.poc.bean.InputBean;
import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.bean.parsing_details.SchemaDetails;
import com.p3.poc.parser.bean.parsing_details.TableDetails;
import com.p3.poc.sunburst_chart.SunBurstGlobalCollector;
import com.p3.poc.sunburst_chart.bean.metadata.ColumnMetadata;
import com.p3.poc.sunburst_chart.bean.metadata.ExportMetadata;
import com.p3.poc.sunburst_chart.bean.metadata.SchemaMetadata;
import com.p3.poc.sunburst_chart.bean.metadata.TableMetadata;
import com.p3.poc.sunburst_chart.utils.commonUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MetadataValidation {

    private final Map<String, List<ColumnDetails>> overAllColumnMap;
    private final Map<String, List<TableDetails>> overAllTableMap;
    private final Map<String, List<SchemaDetails>> overAllSchemaMap;

    private final InputBean inputBean;
    private final commonUtils commonColumProcessUtils;

    final Gson gson = new Gson();
    private SchemaMetadata sm;

    static final String DEFAULT_TABLE_NAME = "DEFAULT_TABLE";


    public MetadataValidation(InputBean inputBean) {
        this.inputBean = inputBean;
        this.commonColumProcessUtils = new commonUtils();
        final SunBurstGlobalCollector instance = SunBurstGlobalCollector.getInstance();
        this.overAllColumnMap = instance.getOverAllColumMap();
        this.overAllTableMap = instance.getOverAllTableMap();
        this.overAllSchemaMap = instance.getOverAllschemaMap();
    }

    public void processMetadata() throws IOException {
        final String metadataFilePath = inputBean.getMetadataFilePath();
        final String metadataValue = FileUtils.readFileToString(new File(metadataFilePath), StandardCharsets.UTF_8);
        ExportMetadata exportMetadata = gson.fromJson(metadataValue, ExportMetadata.class);

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
                    first.ifPresent(schemaMetadata -> processTableValidation(schemaMetadata, schemaDetails));
                }
            }
        }
        overAllColumnMap.remove(DEFAULT_TABLE_NAME);
    }


    private void processTableValidation(SchemaMetadata schema,SchemaDetails schemaDetails) {
        this.sm =schema;
        final List<TableMetadata> metadataTables = schema.getTables();
        final String tableMapKey = commonColumProcessUtils.getCaseInsensitiveMapKey(overAllTableMap, schemaDetails.getId());
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
                first.ifPresent(aBoolean -> processColumnValidations(first.get(), tableDetails));
            }
        }
    }

    private void processColumnValidations(TableMetadata tableMetadata,TableDetails tableDetails) {
        String key = tableDetails.getAliasName().isEmpty() ? DEFAULT_TABLE_NAME : tableDetails.getAliasName();
        final String columnMapKey = commonColumProcessUtils.getCaseInsensitiveMapKey(overAllColumnMap, key);
        if (tableDetails.isHavingDuplicate()){
            handleDuplicateTablesWithDifferentAlias(tableDetails,columnMapKey);
        }
        final List<ColumnDetails> columnDetailsList = overAllColumnMap.get(columnMapKey);
        final List<ColumnMetadata> columnMetadataList = tableMetadata.getColumns();
        Iterator<ColumnDetails> columnIterator = columnDetailsList.iterator();

        while (columnIterator.hasNext()) {
            ColumnDetails columnDetails = columnIterator.next();
            final boolean anyMatch = columnMetadataList.stream().anyMatch(columnMetadata -> columnMetadata.getName()
                    .equalsIgnoreCase(columnDetails.getColumnName()));

            if (!anyMatch && !key.equalsIgnoreCase(DEFAULT_TABLE_NAME)) {
                columnIterator.remove();
            }else if (anyMatch && key.equalsIgnoreCase(DEFAULT_TABLE_NAME)) {
                setColumnValuesToActualTable(tableDetails,columnDetails);
            }
        }
    }

    private void handleDuplicateTablesWithDifferentAlias(TableDetails tableDetails, String key) {
        final List<ColumnDetails> originalColumnDetails = overAllColumnMap.get(key);

        for (String referenceKey : tableDetails.getDuplicateReference()) {
            final List<ColumnDetails> columnDetails = overAllColumnMap.get(referenceKey);
            for (ColumnDetails columnDetail : columnDetails) {
                columnDetail.setColumnAliasName(tableDetails.getAliasName());
                if (originalColumnDetails.stream()
                        .noneMatch(c ->c.getColumnName().equalsIgnoreCase(columnDetail.getColumnName()) )) {
                    originalColumnDetails.add(columnDetail);
                }
            }

            // copy the duplicated columns to original and remove from the map
            overAllColumnMap.remove(referenceKey);
        }
    }

    private void setColumnValuesToActualTable( TableDetails tableDetails, ColumnDetails columnDetails) {
        final String  customAliasName= tableDetails.getName() +"_"+ sm.getName() ;
        tableDetails.setAliasName(customAliasName);
        columnDetails.setColumnSource(customAliasName);
        commonColumProcessUtils.saveColumnDetails(columnDetails);
    }
}
