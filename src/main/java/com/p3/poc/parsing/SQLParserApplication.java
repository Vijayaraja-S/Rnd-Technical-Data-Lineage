package com.p3.poc.parsing;

import com.p3.poc.parsing.bean.SQLQueryDetails;
import com.p3.poc.parsing.command.PopulateDetailsCommand;
import com.p3.poc.parsing.command.SQLCommand;
import com.p3.poc.parsing.node.SQLDetailsPopulatingVisitor;
import com.p3.poc.parsing.node.query_node.SelectNode;
import io.trino.sql.parser.ParsingOptions;
import io.trino.sql.parser.SqlParser;
import io.trino.sql.tree.Query;
import io.trino.sql.tree.Statement;

public class SQLParserApplication {
    private final SQLNodeFactory factory;

    public SQLParserApplication(SQLCommand command, SQLNodeFactory factory) {
        this.factory = factory;
    }

    public SQLQueryDetails parse(String sqlQuery) {
        SqlParser parser = new SqlParser();
        Statement statement = parser.createStatement(sqlQuery, new ParsingOptions());

        if (statement instanceof Query) {
            SelectNode selectNode = (SelectNode) factory.createNode("SELECT");

            SQLDetailsPopulatingVisitor visitor = new SQLDetailsPopulatingVisitor();
            SQLCommand command = new PopulateDetailsCommand(visitor);
            command.execute(selectNode);

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
