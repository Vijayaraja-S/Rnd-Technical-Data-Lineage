package com.p3.poc.parser.parsing.handler.query.service;

import com.p3.poc.parser.bean.query.with.WithInfo;
import io.trino.sql.tree.With;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public interface WithHandler {
    WithInfo handleWith(With with, DefaultDirectedGraph<Object, DefaultEdge> directedGraph);
}
