package com.p3.poc;


import com.p3.poc.result_bean.BeanBuilder;
import com.p3.poc.result_bean.InputBean;
import com.p3.poc.parser.SQLParserApplication;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.exception.InvalidStatement;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InvalidStatement {
        final InputBean inputBean = BeanBuilder.buildInputBean(args[0]);
        final SQLParserApplication app = new SQLParserApplication();
        app.parse(inputBean.getSqlQuery());
        final GlobalCollector instance = GlobalCollector.getInstance();
        System.out.println(instance);
    }
}
