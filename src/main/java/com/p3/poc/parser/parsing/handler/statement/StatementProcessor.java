package com.p3.poc.parser.parsing.handler.statement;

import com.p3.poc.parser.parsing.handler.query.QueryProcessor;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class StatementProcessor extends BaseProcessor {
    private final QueryProcessor queryProcessor;

    public StatementProcessor() {
        queryProcessor = new QueryProcessor();
    }

    public void processQuery(Query query) {
        final List<Node> children = query.getChildren();
        if (!children.isEmpty()) {
            children.forEach(node -> {
                if (node instanceof With) {
                    queryProcessor.handleWith(node);
                }else if (node instanceof QueryBody) {
                    queryProcessor.handleQueryBody(node);
                } else if (node instanceof Limit) {
                    queryProcessor.handleLimit(node);
                } else if (node instanceof Offset) {
                    queryProcessor.handleOffset(node);
                } else if (node instanceof OrderBy) {
                    queryProcessor.handleOrderBy(node);
                }
            });
        } else {
            log.warn("query is empty");
        }
    }

}
