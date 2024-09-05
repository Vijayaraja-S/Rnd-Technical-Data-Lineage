package com.p3.poc;


import com.p3.poc.bean.BeanBuilder;
import com.p3.poc.bean.InputBean;
import com.p3.poc.lineage.process.DirectedGraphBuilder;
import com.p3.poc.parser.SQLParserApplication;
import com.p3.poc.parser.bean.CollectorsUtil;
import com.p3.poc.parser.bean.query.with.WithQueryInfo;
import com.p3.poc.parser.parsing.exception.InvalidStatement;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IOException, InvalidStatement {
        final InputBean inputBean = BeanBuilder.buildInputBean(args[0]);
        final SQLParserApplication app = new SQLParserApplication();
        final DefaultDirectedGraph<Object, DefaultEdge> directedGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
        app.parse(inputBean.getSqlQuery(),directedGraph);
        final Map<String, List<WithQueryInfo>> withQueryInfoMap = CollectorsUtil.withQueryInfoMap;
        new DirectedGraphBuilder().buildGraph(withQueryInfoMap);
    }
}
