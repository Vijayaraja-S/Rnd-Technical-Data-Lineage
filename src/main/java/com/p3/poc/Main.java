package com.p3.poc;


import com.google.gson.Gson;
import com.p3.poc.bean.BeanBuilder;
import com.p3.poc.bean.InputBean;
import com.p3.poc.parser.SQLParserApplication;

import com.p3.poc.parser.bean.CollectorsUtil;
import com.p3.poc.parser.bean.query.BaseQueryInfo;
import com.p3.poc.parser.bean.query.with.WithQueryInfo;
import com.p3.poc.parser.parsing.exception.InvalidStatement;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IOException, InvalidStatement {
        final InputBean inputBean = BeanBuilder.buildInputBean(args[0]);
        final SQLParserApplication app = new SQLParserApplication();
        final BaseQueryInfo parse = app.parse(inputBean.getSqlQuery());
        final Map<String, List<WithQueryInfo>> withQueryInfoMap = CollectorsUtil.withQueryInfoMap;
        System.out.println(parse.toString());
        Gson gson = new Gson();
        System.out.println(gson.toJson(parse));
    }
}
