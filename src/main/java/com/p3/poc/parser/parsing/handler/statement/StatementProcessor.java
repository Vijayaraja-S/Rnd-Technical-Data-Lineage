package com.p3.poc.parser.parsing.handler.statement;

import com.google.common.graph.Graph;
import com.p3.poc.parser.bean.query.BaseQueryInfo;
import com.p3.poc.parser.parsing.handler.query.QueryHandler;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class StatementProcessor {
    private final QueryHandler queryHandler;

    public StatementProcessor() {
        queryHandler = new QueryHandler();
    }

    public BaseQueryInfo processQuery(Query query) {
        final BaseQueryInfo queryInfo = BaseQueryInfo.builder().build();
        final List<Node> children = query.getChildren();
         if (!children.isEmpty()) {
            children.forEach(node -> {
                if (node instanceof QueryBody) {
                    queryInfo.setBaseQueryBodyInfo(queryHandler.handleQueryBody(node));
                } else if (node instanceof With) {
                    queryInfo.setWithInfo(queryHandler.handleWith(node));
                } else if (node instanceof Limit) {
                    queryInfo.setLimitInfo(queryHandler.handleLimit(node));
                } else if (node instanceof Offset) {
                    queryInfo.setOffsetInfo(queryHandler.handleOffset(node));
                } else if (node instanceof OrderBy) {
                    queryInfo.setOffsetInfo(queryHandler.handleOrderBy(node));
                }
            });
        } else {
            log.warn("query is empty");
        }
        return queryInfo;
    }

}
