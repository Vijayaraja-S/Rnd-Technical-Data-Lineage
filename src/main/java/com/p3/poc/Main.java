package com.p3.poc;


import com.p3.poc.bean.BeanBuilder;
import com.p3.poc.bean.InputBean;
import com.p3.poc.chart.SunburstChartHelper;
import com.p3.poc.parser.SQLParserApplication;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.exception.InvalidStatement;
import org.json.JSONArray;

import java.io.IOException;


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
    }
}
