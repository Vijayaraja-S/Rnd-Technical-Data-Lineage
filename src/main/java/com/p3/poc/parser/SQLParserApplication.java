package com.p3.poc.parser;

import com.p3.poc.parser.bean.SQLQueryDetails;
import com.p3.poc.parser.command.PopulateDetailsCommand;
import com.p3.poc.parser.command.SQLCommand;
import com.p3.poc.parser.node.SQLDetailsPopulatingVisitor;
import com.p3.poc.parser.node.query_node.SelectNode;
import io.trino.sql.parser.ParsingOptions;
import io.trino.sql.parser.SqlParser;
import io.trino.sql.tree.Node;
import io.trino.sql.tree.Query;
import io.trino.sql.tree.Statement;

import java.util.List;

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
        final List<? extends Node> children = statement.getChildren();

        if (statement instanceof Query) {
            SelectNode selectNode = (SelectNode) factory.createNode("SELECT");

            SQLDetailsPopulatingVisitor visitor = new SQLDetailsPopulatingVisitor();
            SQLCommand cd = new PopulateDetailsCommand(visitor);
            cd.execute(selectNode);

            return visitor.getSqlQueryDetails();
        }

        return null;
    }

    private String[] extractSelectParams(Query query) {
        return new String[]{};
    }

    private String[] extractFromParams(Query query) {
        return new String[]{};
    }

    private String[] extractJoinParams(Query query) {
        return new String[]{};
    }

    private String[] extractWhereParams(Query query) {
        return new String[]{};
    }

    private String[] extractGroupByParams(Query query) {
        return new String[]{};
    }

}
