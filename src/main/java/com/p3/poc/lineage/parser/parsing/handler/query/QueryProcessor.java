package com.p3.poc.lineage.parser.parsing.handler.query;

import com.p3.poc.lineage.parser.parsing.handler.query.service.OtherQueryHandler;
import com.p3.poc.lineage.parser.parsing.handler.query.service.WithHandler;
import com.p3.poc.lineage.parser.parsing.handler.query.service_impl.OtherQueryHandleImpl;
import com.p3.poc.lineage.parser.parsing.handler.query.service_impl.WithHandlerImpl;
import io.trino.sql.tree.Node;
import io.trino.sql.tree.QueryBody;
import io.trino.sql.tree.With;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueryProcessor {

    private final OtherQueryHandler otherQueryHandler;
    private final WithHandler withHandler;


    public QueryProcessor() {
        this.otherQueryHandler = new OtherQueryHandleImpl();
        this.withHandler = new WithHandlerImpl();
    }

    public void handleWith(Node node) {
        final With with = (With) node;
        withHandler.handleWith(with);
    }

    public void handleQueryBody(Node node) {
        final QueryBody queryBody = (QueryBody) node;
        otherQueryHandler.handleQueryBody(queryBody);
    }

    public void handleOffset(Node node) {
        otherQueryHandler.handleOffset(node);
    }

    public void handleOrderBy(Node node) {
        otherQueryHandler.handleOrderBy(node);
    }

    public void handleLimit(Node node) {
        otherQueryHandler.handleLimit(node);
    }
}
