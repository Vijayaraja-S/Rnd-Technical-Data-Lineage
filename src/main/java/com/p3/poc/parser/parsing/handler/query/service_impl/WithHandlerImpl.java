package com.p3.poc.parser.parsing.handler.query.service_impl;


import com.p3.poc.parser.bean.CollectorsUtil;
import com.p3.poc.parser.bean.query.BaseQueryInfo;
import com.p3.poc.parser.bean.query.with.WithInfo;
import com.p3.poc.parser.bean.query.with.WithQueryInfo;
import com.p3.poc.parser.parsing.handler.query.service.WithHandler;
import com.p3.poc.parser.parsing.handler.statement.StatementProcessor;
import io.trino.sql.tree.Identifier;
import io.trino.sql.tree.With;
import io.trino.sql.tree.WithQuery;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class WithHandlerImpl implements WithHandler {
    @Override
    public WithInfo handleWith(With with, DefaultDirectedGraph<Object, DefaultEdge> directedGraph) {
        final WithInfo withInfo = WithInfo.builder()
                .id("with:" + CollectorsUtil.getWithCount())
                .isrecursive(with.isRecursive())
                .build();
        CollectorsUtil.withInfoMap.put(withInfo.getId(),withInfo);

        final List<WithQuery> queries = with.getQueries();
        final List<WithQueryInfo> withQueryInfos = queries.stream()
                .map(withQuery -> {
                    final Optional<List<Identifier>> columnNames = withQuery.getColumnNames();
                    final BaseQueryInfo query = new StatementProcessor().processQuery(withQuery.getQuery(), directedGraph);
                    return WithQueryInfo.builder()
                            .beanId("WithQueryInfo:" + CollectorsUtil.getWithQueryInfoCount())
                            .name(String.valueOf(withQuery.getName()))
                            .columnNames(columnNames.orElseGet(ArrayList::new))
                            .query(query)
                            .withReferenceId(withInfo.getId())
                            .build();
                }).toList();

        CollectorsUtil.withQueryInfoMap.put(withInfo.getId(), withQueryInfos);
        return withInfo;
    }
}



