package com.p3.poc;

import com.google.gson.Gson;
import com.p3.poc.bean.BeanBuilder;
import com.p3.poc.bean.InputBean;
import com.p3.poc.constants.RegexConstant;
import com.p3.poc.parser.SQLParserApplication;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.exception.InvalidStatement;
import com.p3.poc.sunburst_chart.SunBurstGlobalCollector;
import com.p3.poc.sunburst_chart.bean.metadata.ExportMetadata;
import com.p3.poc.sunburst_chart.bean.metadata.SchemaMetadata;
import com.p3.poc.sunburst_chart.chart.SunburstChartHelper;
import com.p3.poc.sunburst_chart.bean.InputQueryBean;
import com.p3.poc.sunburst_chart.bean.InputQueryDetails;
import com.p3.poc.sunburst_chart.service.MetadataValidation;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryProcessor {
    final Gson gson = new Gson();

    public InputBean initializeCollectors(String inputFilePath) throws IOException {
        InputBean inputBean = BeanBuilder.buildInputBean(inputFilePath);

        // Set search ID and search name for both collectors
        final SunBurstGlobalCollector instance = SunBurstGlobalCollector.getInstance();
        final GlobalCollector globalCollector = GlobalCollector.getInstance();
        setSearchDetails(instance, inputBean, globalCollector);
        return inputBean;
    }

    private  void setSearchDetails(SunBurstGlobalCollector instance, InputBean inputBean, GlobalCollector globalCollector) {
        instance.setSearchId(inputBean.getSearchId());
        instance.setSearchName(inputBean.getSearchName());
        globalCollector.setSearchId(inputBean.getSearchId());
        globalCollector.setSearchName(inputBean.getSearchName());
    }


    public void processInputFile(InputBean inputBean) throws IOException, InvalidStatement {
        String inputFileContent = readJsonFile(inputBean.getInputFilePath());
        InputQueryBean inputQueryBean = gson.fromJson(inputFileContent, InputQueryBean.class);

        for (InputQueryDetails queryDetails : inputQueryBean.getInputQueryDetails()) {
            String executableQuery = convertToExecutableQuery(queryDetails.getQuery());
            JSONArray queryResult = processExecutableQuery(executableQuery, inputBean);

            System.err.println("Query: " + queryDetails.getQueryName());
            System.err.println("Results: " + queryResult);

            SunBurstGlobalCollector.getInstance().resetCollector();
        }
    }


    private String readJsonFile(String inputFilePath) throws IOException {
        return FileUtils.readFileToString(new File(inputFilePath), StandardCharsets.UTF_8);
    }


    private String convertToExecutableQuery(String query) {

        Pattern fieldPattern = Pattern.compile(RegexConstant.INPUT_REPLACER_REGEX, Pattern.MULTILINE);
        Pattern verificationPattern =
                Pattern.compile(RegexConstant.INPUT_FIELD_REGEX, Pattern.MULTILINE);
        Matcher fieldMatcher = fieldPattern.matcher(query);
        Map<String, String> matcherString = new HashMap<>();
        while (fieldMatcher.find()) {
            String group = fieldMatcher.group(0);
            if (!group.trim().isEmpty() && verificationPattern.matcher(group.trim()).find()) {
                matcherString.put("@@" + group + "@@", "");
            }
        }
        for (Map.Entry<String, String> stringStringEntry : matcherString.entrySet()) {
            query = query.replace(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        return query;
    }


    private JSONArray processExecutableQuery(String executableQuery, InputBean inputBean) throws InvalidStatement, IOException {
        SQLParserApplication parser = new SQLParserApplication();

        parser.parse(executableQuery, inputBean);
        final String metadataFilePath = inputBean.getMetadataFilePath();
        final String metadataValue = readJsonFile(metadataFilePath);
        final ExportMetadata metadata = gson.fromJson(metadataValue, ExportMetadata.class);
        final MetadataValidation metadataValidation = new MetadataValidation(metadata);
        if (inputBean.isDoMetadataValidation()){
            metadataValidation.processMetadata();
        }

        SunburstChartHelper chartHelper = new SunburstChartHelper();
        return chartHelper.generateSunburst();
    }
}
