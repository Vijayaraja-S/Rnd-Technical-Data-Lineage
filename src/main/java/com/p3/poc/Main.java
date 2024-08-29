package com.p3.poc;


import com.google.gson.Gson;
import com.p3.poc.bean.BeanBuilder;
import com.p3.poc.bean.InputBean;
import com.p3.poc.parser.SQLParserApplication;

import com.p3.poc.parser.bean.query.BaseQueryInfo;
import com.p3.poc.parser.parsing.exception.InvalidStatement;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InvalidStatement {
        final InputBean inputBean = BeanBuilder.buildInputBean(args[0]);
        final SQLParserApplication app = new SQLParserApplication();
        final BaseQueryInfo parse = app.parse(inputBean.getSqlQuery());
        Gson gson = new Gson();
        System.out.println(gson.toJson(parse));
    }
}
