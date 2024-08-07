package com.p3.poc.query_processor;

import io.trino.sql.parser.ParsingOptions;
import io.trino.sql.parser.SqlParser;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.Node;
import io.trino.sql.tree.Statement;
import io.trino.sql.tree.Table;

import java.util.HashSet;
import java.util.Set;


public class QueryProcessor {
    private String parsableQueryCleanup(String sql) {
        return sql.replace("@@", "").replaceAll("\\$\\{(.*?)}", "123");
    }

    public Set<String> getTableListing(String query) {
        query = parsableQueryCleanup(query);
        Set<String> list = new HashSet<>();
//        Expression expression = new SqlParser().createExpression(query, new ParsingOptions());
        Statement statement = new SqlParser().createStatement(query, new ParsingOptions());
        processNode(statement, list);
        return list;
    }

    private void processNode(Node node, Set<String> list) {
        node.getChildren()
                .forEach(
                        child -> {
                            if (child instanceof Table) {
                                list.add(((Table) child).getName().toString());
                            } else {
                                processNode(child, list);
                            }
                        });
    }
}
