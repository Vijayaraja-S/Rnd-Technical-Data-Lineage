package com.p3.poc.parser.parsing.handler.statement;

import io.trino.sql.tree.Query;
import io.trino.sql.tree.Statement;

public class StatementHandler {

    public void handleQuery(Statement statement) {
        final Query query = (Query) statement;
        new StatementProcessor().processQuery(query);
    }
}
