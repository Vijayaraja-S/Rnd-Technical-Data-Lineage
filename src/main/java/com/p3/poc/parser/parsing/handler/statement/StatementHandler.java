package com.p3.poc.parser.parsing.handler.statement;

import io.trino.sql.tree.Query;
import io.trino.sql.tree.Statement;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class StatementHandler {
    private final StatementProcessor statementProcessor;

    public StatementHandler() {
        this.statementProcessor = new StatementProcessor();
    }

    public void handleQuery(Statement statement, DefaultDirectedGraph<Object, DefaultEdge> directedGraph) {
        final Query query = (Query) statement;
        statementProcessor.processQuery(query,directedGraph);
    }
}
