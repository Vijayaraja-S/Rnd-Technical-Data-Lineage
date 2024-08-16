package com.p3.poc.parser.parsing.handler.query.operations;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.WithQueryObjectDetails;
import com.p3.poc.parser.parsing.handler.query.QueryProcessor;
import io.trino.sql.tree.Identifier;
import io.trino.sql.tree.Node;
import io.trino.sql.tree.With;
import io.trino.sql.tree.WithQuery;

import java.util.List;
import java.util.Optional;

public class WithHandler implements QueryProcessor {
    private final QueryParsedDetails queryDetails;
    private WithQueryObjectDetails withQueryObjectDetails;

    public WithHandler(QueryParsedDetails queryDetails) {
        this.queryDetails = queryDetails;
        this.withQueryObjectDetails = new WithQueryObjectDetails();
    }


    @Override
    public void processQueryObject(Node node) {
        final With withNode = (With) node;

        final boolean recursive = withNode.isRecursive();

        final List<WithQuery> withQuery = withNode.getQueries();
        if (!withQuery.isEmpty()) {
            for (WithQuery query : withQuery) {
                final Optional<List<Identifier>> columnNames = query.getColumnNames();
                final Identifier name = query.getName();
                final List<Node> queryObject = query.getChildren();
                if (!queryObject.isEmpty()) {
                }
            }
        }

    }
}
