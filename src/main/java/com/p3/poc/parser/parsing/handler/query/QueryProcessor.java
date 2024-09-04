package com.p3.poc.parser.parsing.handler.query;

import com.p3.poc.parser.bean.CollectorsUtil;
import com.p3.poc.parser.bean.query.BaseQueryInfo;
import com.p3.poc.parser.bean.query.query_body.BaseQueryBodyInfo;
import com.p3.poc.parser.bean.query.with.WithInfo;
import com.p3.poc.parser.bean.query.with.WithQueryInfo;
import com.p3.poc.parser.parsing.handler.query_body.QueryBodyHandler;
import com.p3.poc.parser.parsing.handler.statement.StatementProcessor;
import io.trino.sql.tree.*;

import java.util.*;

public class QueryProcessor {

    private static String  referenceId = "root";
    private final QueryBodyHandler queryBodyHandler;

    public QueryProcessor() {
        this.queryBodyHandler = new QueryBodyHandler();
    }

    public BaseQueryBodyInfo processQueryBody(QueryBody queryBody) {
        return queryBodyHandler.handleQueryBody(queryBody);
    }

    public WithInfo processWith(With with) {
        final int withCount = CollectorsUtil.getWithCount();
        final WithInfo withInfo = WithInfo.builder()
                .id("with:"+ withCount)
                .isrecursive(with.isRecursive())
                .build();

        final List<WithQuery> queries = with.getQueries();
        final List<WithQueryInfo>withQueryInfos = queries.stream()
                .map(withQuery -> {
                    final Optional<List<Identifier>> columnNames = withQuery.getColumnNames();
                    final BaseQueryInfo query = new StatementProcessor().processQuery(withQuery.getQuery());
                    return WithQueryInfo.builder()
                            .beanId("WithQueryInfo:"+ CollectorsUtil.getWithQueryInfoCount())
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
