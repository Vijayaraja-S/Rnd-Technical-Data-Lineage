package com.p3.poc.parser;

import com.p3.poc.parser.bean.SQLQueryDetails;
import com.p3.poc.parser.command.PopulateDetailsCommand;
import com.p3.poc.parser.command.SQLCommand;
import com.p3.poc.parser.composite.query_node.FromNode;
import com.p3.poc.parser.factory.SQLNodeFactory;
import com.p3.poc.parser.visitors.SQLDetailsPopulatingVisitor;
import io.trino.sql.parser.ParsingOptions;
import io.trino.sql.parser.SqlParser;
import io.trino.sql.tree.Query;
import io.trino.sql.tree.Statement;

public class SQLParserApplication {
    private final SQLCommand command;
    private final SQLNodeFactory factory;

    public SQLParserApplication(SQLCommand command, SQLNodeFactory factory) {
        this.command = command;
        this.factory = factory;
    }

    public SQLQueryDetails parse(String sqlQuery) {
        SqlParser parser = new SqlParser();
        Statement statement = parser.createStatement(sqlQuery, new ParsingOptions());
        if (statement instanceof Query query) {
            final FromNode fromNode = (FromNode) factory.createNode("FROM",extractFromParams(query));

            SQLDetailsPopulatingVisitor visitor = new SQLDetailsPopulatingVisitor();
            SQLCommand cd = new PopulateDetailsCommand(visitor);
            cd.execute(fromNode);

            return visitor.getSqlQueryDetails();
        }

        return null;
    }

    private String[] extractFromParams(Query query) {
        return new String[]{};
    }

}
