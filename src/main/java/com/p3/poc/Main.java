package com.p3.poc;


import com.p3.poc.bean.BeanBuilder;
import com.p3.poc.bean.InputBean;
import com.p3.poc.chart.SunburstChartHelper;
import com.p3.poc.parser.SQLParserApplication;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.exception.InvalidStatement;
import io.trino.jdbc.$internal.jackson.core.JsonProcessingException;
import org.json.JSONArray;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) throws IOException, InvalidStatement {
        final GlobalCollector instance = GlobalCollector.getInstance();
        final InputBean inputBean = BeanBuilder.buildInputBean(args[0]);
        instance.setSearchId(inputBean.getSearchId());
        instance.setSearchName(inputBean.getSearchName());
        final SQLParserApplication app = new SQLParserApplication();
        app.parse(inputBean.getSqlQuery());
        final SunburstChartHelper sunburstChartHelper = new SunburstChartHelper();
        final JSONArray objects = sunburstChartHelper.generateSunburst();
        System.out.println(objects);
//        final String s = convertToExecutableQuery(inputBean.getSqlQuery());
//        System.out.println(s);

    }

    public static String convertToExecutableQuery(String query) throws   JsonProcessingException {
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
