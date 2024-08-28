package com.p3.poc.parser.parsing.handler.query_body;

import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecDetails;
import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecType;
import com.p3.poc.parser.bean.query.query_body.QuerySpecificationDetails;
import com.p3.poc.parser.parsing.handler.query_specification.QuerySpecHandler;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.QuerySpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueryBodyProcessor extends QueryBodyHelper {
    private final  QuerySpecHandler querySpecHandler;
    public QueryBodyProcessor() {
        this.querySpecHandler = new QuerySpecHandler();
    }

    public  QuerySpecificationDetails processQueryBody(QuerySpecification querySpecification) {
        final QuerySpecificationDetails querySpec= new QuerySpecificationDetails();
        var children = querySpecification.getChildren();
        if (!children.isEmpty()) {
            children
                    .forEach(child -> {
                        if (!(child instanceof Expression)) {
                            final QuerySpecDetails commonQuerySpec = querySpecHandler.handleExpression(child);
                            querySpec.getSpecificationBeanHashMap()
                                    .put(commonQuerySpec.getQueryType(), commonQuerySpec);
                        }
                    });
            querySpec.getSpecificationBeanHashMap()
                    .put(QuerySpecType.HAVING, getHavingQueryInfo(querySpecification));
            querySpec.getSpecificationBeanHashMap()
                    .put(QuerySpecType.WHERE,  getWhereQueryInfo(querySpecification));
        }
        return querySpec;
    }
}
