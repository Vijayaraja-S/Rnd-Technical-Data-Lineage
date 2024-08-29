package com.p3.poc.parser.parsing.handler.statement;

import com.p3.poc.parser.bean.query.BaseQueryInfo;
import io.trino.sql.tree.Query;
import io.trino.sql.tree.Statement;

public class StatementHandler {
    private final StatementProcessor statementProcessor;

    public StatementHandler() {
        this.statementProcessor = new StatementProcessor();
    }

    public BaseQueryInfo handleQuery(Statement statement) {
        final Query query = (Query) statement;
        return statementProcessor.processQuery(query);
    }
}
