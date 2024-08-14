package com.p3.poc;


import com.google.gson.Gson;
import com.p3.poc.bean.BeanBuilder;
import com.p3.poc.bean.InputBean;
import com.p3.poc.parser.factory.SQLNodeFactory;
import com.p3.poc.parser.SQLParserApplication;
import com.p3.poc.parser.bean.SQLQueryDetails;
import com.p3.poc.parser.command.ProcessBaseNodes;
import com.p3.poc.parser.command.BaseNodes;
import com.p3.poc.parser.visitors.SQLDetailsPopulatingVisitor;

import java.io.IOException;

import static java.lang.System.out;


public class Main {
    public static void main(String[] args) throws IOException {
        final InputBean inputBean = BeanBuilder.buildInputBean(args[0]);
        final SQLParserApplication app = initParsing();
        SQLQueryDetails details = app.parse(inputBean.getSqlQuery());
        out.println(new Gson().toJson(details));
    }

    private static SQLParserApplication initParsing() {
        SQLDetailsPopulatingVisitor visitor = new SQLDetailsPopulatingVisitor();
        SQLNodeFactory factory = SQLNodeFactory.getFactory();
        BaseNodes command = new ProcessBaseNodes(visitor);
        return new SQLParserApplication(command, factory);
    }
}
