package com.p3.poc.parser;

import com.p3.poc.parser.bean.SQLQueryDetails;
import com.p3.poc.parser.command.ProcessBaseNodes;
import com.p3.poc.parser.command.BaseNodes;
import com.p3.poc.parser.factory.SQLNodeFactory;
import com.p3.poc.parser.visitors.SQLDetailsPopulatingVisitor;
import io.trino.sql.parser.ParsingOptions;
import io.trino.sql.parser.SqlParser;
import io.trino.sql.tree.Node;
import io.trino.sql.tree.Query;
import io.trino.sql.tree.Statement;

public class SQLParserApplication {
    private final BaseNodes command;
    private final SQLNodeFactory factory;

    public SQLParserApplication(BaseNodes command, SQLNodeFactory factory) {
        this.command = command;
        this.factory = factory;
    }

    public SQLQueryDetails parse(String sqlQuery) {
        SqlParser parser = new SqlParser();
        Statement statement = parser.createStatement(sqlQuery, new ParsingOptions());
        if (statement instanceof Query query) {
            for (Node child : query.getChildren()) {

            }
        }

        return null;
    }


}
