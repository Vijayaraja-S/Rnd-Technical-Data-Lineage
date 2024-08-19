package com.p3.poc;


import com.google.gson.Gson;
import com.p3.poc.bean.BeanBuilder;
import com.p3.poc.bean.InputBean;
import com.p3.poc.parser.SQLParserApplication;
import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.parsing.exception.InvalidStatement;

import java.io.IOException;

import static java.lang.System.out;


public class Main {
    public static void main(String[] args) throws IOException, InvalidStatement {
        final InputBean inputBean = BeanBuilder.buildInputBean(args[0]);
        final SQLParserApplication app = new SQLParserApplication();
        QueryParsedDetails details = app.parse(inputBean.getSqlQuery());
        out.println(new Gson().toJson(details));
    }
}
