package com.p3.poc.lineage.parser.parsing.handler.statement;

import io.trino.sql.tree.Query;

public class StatementHandler {

    public void handleQuery(Query query) {
        new StatementProcessor().processQuery(query);
    }
}
