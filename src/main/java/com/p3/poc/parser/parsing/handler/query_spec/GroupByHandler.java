package com.p3.poc.parser.parsing.handler.query_spec;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.having.HavingQueryInfo;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import com.p3.poc.parser.parsing.handler.expression.CommonExpressionHandler;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.GroupBy;
import lombok.Data;

@Data
public class GroupByHandler implements CommonQueryParser {
    private QueryParsedDetails queryParsedDetails;
    private CommonExpressionHandler commonExpressionHandler;

    public GroupByHandler(QueryParsedDetails queryParsedDetails) {
        this.queryParsedDetails = queryParsedDetails;
        this.commonExpressionHandler = new CommonExpressionHandler();
    }


    @Override
    public void processQuery(Object node) {
        final GroupBy groupBy = (GroupBy) node;

        //
    }
}
