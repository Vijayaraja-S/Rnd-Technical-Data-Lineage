package com.p3.poc;


import com.p3.poc.bean.BeanBuilder;
import com.p3.poc.bean.InputBean;
import com.p3.poc.parser.SQLParserApplication;
import com.p3.poc.parser.parsing.exception.InvalidStatement;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InvalidStatement {
        final InputBean inputBean = BeanBuilder.buildInputBean(args[0]);
        final DefaultDirectedGraph<Object, DefaultEdge> directedGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
        final SQLParserApplication app = new SQLParserApplication();
        app.parse(inputBean.getSqlQuery(),directedGraph);
    }
}
