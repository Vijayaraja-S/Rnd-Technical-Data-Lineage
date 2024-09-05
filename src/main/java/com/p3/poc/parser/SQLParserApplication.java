package com.p3.poc.parser;

import com.p3.poc.parser.parsing.exception.InvalidStatement;
import com.p3.poc.parser.parsing.handler.statement.StatementHandler;
import io.trino.sql.parser.ParsingOptions;
import io.trino.sql.parser.SqlParser;
import io.trino.sql.tree.Query;
import io.trino.sql.tree.Statement;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

@Slf4j
public class SQLParserApplication {
    private final StatementHandler statementHandler;

    public SQLParserApplication() {
        this.statementHandler = new StatementHandler();
    }

    public void parse(String sqlQuery, DefaultDirectedGraph<Object, DefaultEdge> directedGraph) throws InvalidStatement {
        SqlParser parser = new SqlParser();
        Statement statement = parser.createStatement(sqlQuery, new ParsingOptions());
        if (statement instanceof Query query) {
            statementHandler.handleQuery(query,directedGraph);
        } else {
            throw new InvalidStatement("Invalid statement object");
        }
    }

}
