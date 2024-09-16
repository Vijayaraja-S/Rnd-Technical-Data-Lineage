package com.p3.poc;


import com.google.gson.Gson;
import com.p3.poc.bean.BeanBuilder;
import com.p3.poc.bean.InputBean;
import com.p3.poc.parser.SQLParserApplication;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.exception.InvalidStatement;
import com.p3.poc.sunburst_chart.SunBurstGlobalCollector;
import com.p3.poc.sunburst_chart.SunburstChartHelper;
import com.p3.poc.sunburst_chart.bean.InputQueryBean;
import com.p3.poc.sunburst_chart.bean.InputQueryDetails;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) throws IOException, InvalidStatement {
        final SunBurstGlobalCollector instance = SunBurstGlobalCollector.getInstance();
        final GlobalCollector globalCollectorInstance = GlobalCollector.getInstance();

        final Gson gson = new Gson();

        final InputBean inputBean = BeanBuilder.buildInputBean(args[0]);

        instance.setSearchId(inputBean.getSearchId());
        instance.setSearchName(inputBean.getSearchName());

        globalCollectorInstance.setSearchId(inputBean.getSearchId());
        globalCollectorInstance.setSearchName(inputBean.getSearchName());

        final String inputFile = convertJsonInputFile(inputBean.getInputFilePath());
        final InputQueryBean inputQueryBean = gson.fromJson(inputFile, InputQueryBean.class);

        for (InputQueryDetails inputQueryDetail : inputQueryBean.getInputQueryDetails()) {
            final String toExecutableQuery = convertToExecutableQuery(inputQueryDetail.getQuery());
            final JSONArray objects = processExecutableQuery(toExecutableQuery, inputBean);
            System.err.println(inputQueryDetail.getQueryName());
            System.err.println(objects);
        }
    }

    private static JSONArray processExecutableQuery(String toExecutableQuery, InputBean inputBean) throws InvalidStatement {
        final SQLParserApplication app = new SQLParserApplication();
        app.parse(toExecutableQuery, inputBean);
        final SunburstChartHelper sunburstChartHelper = new SunburstChartHelper();
        return sunburstChartHelper.generateSunburst();
    }

    private static String convertJsonInputFile(String inputFilePath) throws IOException {
        return FileUtils.readFileToString(new File(inputFilePath), StandardCharsets.UTF_8);
    }

    public static String convertToExecutableQuery(String query) {
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
}
