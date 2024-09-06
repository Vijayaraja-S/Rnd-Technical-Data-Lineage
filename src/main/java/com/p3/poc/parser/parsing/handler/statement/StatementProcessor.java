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

    public StatementProcessor(DefaultDirectedGraph<Object, DefaultEdge> directedGraph) {
        queryProcessor = new QueryProcessor(directedGraph);
    }

    public BaseQueryInfo processQuery(Query query) {
        final BaseQueryInfo queryInfo = BaseQueryInfo.builder().build();
        final List<Node> children = query.getChildren();
        if (!children.isEmpty()) {
            children.forEach(node -> {
                if (node instanceof With) {
                    queryInfo.setWithInfo(queryProcessor.handleWith(node));
                }else if (node instanceof QueryBody) {
                    queryInfo.setBaseQueryBodyInfo(queryProcessor.handleQueryBody(node));
                } else if (node instanceof Limit) {
                    queryInfo.setLimitInfo(queryProcessor.handleLimit(node));
                } else if (node instanceof Offset) {
                    queryInfo.setOffsetInfo(queryProcessor.handleOffset(node));
                } else if (node instanceof OrderBy) {
                    queryInfo.setOrderByInfo(queryProcessor.handleOrderBy(node));
                }
            });
        } else {
            log.warn("query is empty");
        }
        return queryInfo;
    }

}
