package com.p3.poc.parser.parsing.handler.query_body;

import com.p3.poc.parser.bean.query.query_body.query_specification.others.HavingQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.WhereQueryInfo;
import com.p3.poc.parser.parsing.handler.query_specification.QuerySpecHandler;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.QuerySpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
@Slf4j
public class QueryBodyHelper {
    private final QuerySpecHandler commonQueryHandler;

    public QueryBodyHelper() {
        commonQueryHandler = new QuerySpecHandler();
    }

    public HavingQueryInfo getHavingQueryInfo(QuerySpecification querySpecNode) {
        final Optional<Expression> having = querySpecNode.getHaving();
        if (having.isPresent()) {
            final Expression havingValue = having.get();
            return commonQueryHandler.handleHaving(havingValue);
        }
        return HavingQueryInfo.getBean();
    }

    public WhereQueryInfo getWhereQueryInfo(QuerySpecification querySpecNode) {
        final Optional<Expression> where = querySpecNode.getWhere();
        if (where.isPresent()) {
            final Expression whereExp = where.get();
            return commonQueryHandler.handleWhere(whereExp);
        }
        return WhereQueryInfo.getBean();
    }
}
