package com.p3.poc.bean.query_processor;

import com.p3.poc.bean.response.ColumnInfo;
import com.p3.poc.bean.response.QueryResultBean;
import com.p3.poc.bean.response.TableInfo;
import io.trino.sql.parser.ParsingOptions;
import io.trino.sql.parser.SqlParser;
import io.trino.sql.tree.Node;
import io.trino.sql.tree.QuerySpecification;
import io.trino.sql.tree.Relation;
import io.trino.sql.tree.Statement;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
public class QueryProcessor {
    private String query;
    private Map<String,List<ColumnInfo>> columnListMap = new HashMap<>();
    private Map<String,List<TableInfo>> tableListMap = new HashMap<>();

    public QueryProcessor(String query) {this.query = query;}

    private String parsableQueryCleanup() {
        return query.replace("@@", "").replaceAll("\\$\\{(.*?)}", "123");
    }

    public void init() {
        query = parsableQueryCleanup();
        Statement statement = new SqlParser().createStatement(query, new ParsingOptions());
        final QueryResultBean queryResultBean = QueryResultBean.builder().build();
        getApplicationSchemaTableInfo(statement);
    }

    private void getApplicationSchemaTableInfo(Node node) {
        final List<? extends Node> children = node.getChildren();
        for (Node child : children) {
            final boolean checkQSInstance = child instanceof QuerySpecification;
            if (checkQSInstance) {
               final Optional<Relation> from = ((QuerySpecification) child).getFrom();
               if (from.isPresent()) {
                   final Relation fromClause = from.get();
                   final List<? extends Node> fromClauseChildren = fromClause.getChildren();
                   final int size = fromClauseChildren.size();
                   for (int i = 0; i < size; i++) {

                   }
               }
           }
        }
    }


}
