package com.p3.poc.parser.parsing.handler.statement;

import com.p3.poc.parser.bean.query.BaseQueryInfo;
import com.p3.poc.parser.parsing.handler.query.QueryProcessor;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

@Slf4j
public class StatementProcessor extends BaseProcessor {
    private final QueryProcessor queryProcessor;

    public StatementProcessor() {
        queryProcessor = new QueryProcessor();
    }

    public BaseQueryInfo processQuery(Query query, DefaultDirectedGraph<Object, DefaultEdge> directedGraph) {
        final BaseQueryInfo queryInfo = BaseQueryInfo.builder().build();
        final List<Node> children = query.getChildren();
        if (!children.isEmpty()) {
            children.forEach(node -> {
                if (node instanceof QueryBody) {
                    queryInfo.setBaseQueryBodyInfo(queryProcessor.handleQueryBody(node,directedGraph));
                } else if (node instanceof With) {
                    queryInfo.setWithInfo(queryProcessor.handleWith(node,directedGraph));
                } else if (node instanceof Limit) {
                    queryInfo.setLimitInfo(queryProcessor.handleLimit(node,directedGraph));
                } else if (node instanceof Offset) {
                    queryInfo.setOffsetInfo(queryProcessor.handleOffset(node,directedGraph));
                } else if (node instanceof OrderBy) {
                    queryInfo.setOrderByInfo(queryProcessor.handleOrderBy(node,directedGraph));
                }
            });
        } else {
            log.warn("query is empty");
        }
        return queryInfo;
    }

}
