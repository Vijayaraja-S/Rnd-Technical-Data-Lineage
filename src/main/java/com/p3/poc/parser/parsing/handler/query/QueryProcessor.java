package com.p3.poc.parser.parsing.handler.query;

import com.p3.poc.parser.bean.query.query_body.BaseQueryBodyInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import com.p3.poc.parser.bean.query.with.WithInfo;
import com.p3.poc.parser.parsing.handler.query.service.*;
import com.p3.poc.parser.parsing.handler.query.service_impl.*;
import io.trino.sql.tree.Node;
import io.trino.sql.tree.QueryBody;
import io.trino.sql.tree.With;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

@Slf4j
public class QueryProcessor {

    private final OtherQueryHandler otherQueryHandler;
    private final WithHandler withHandler;

    public QueryProcessor() {
        this.otherQueryHandler = new OtherQueryHandleImpl();
        this.withHandler = new WithHandlerImpl();
    }

    public WithInfo handleWith(Node node, DefaultDirectedGraph<Object, DefaultEdge> directedGraph) {
        final With with = (With) node;
        return withHandler.handleWith(with,directedGraph);
    }

    public BaseQueryBodyInfo handleQueryBody(Node node, DefaultDirectedGraph<Object, DefaultEdge> directedGraph) {
        final QueryBody queryBody = (QueryBody) node;
        return otherQueryHandler.handleQueryBody(queryBody,directedGraph);
    }

    public OffsetInfo handleOffset(Node node, DefaultDirectedGraph<Object, DefaultEdge> directedGraph) {
        return otherQueryHandler.handleOffset(node,directedGraph);
    }

    public OrderByInfo handleOrderBy(Node node, DefaultDirectedGraph<Object, DefaultEdge> directedGraph) {
        return otherQueryHandler.handleOrderBy(node,directedGraph);
    }

    public LimitInfo handleLimit(Node node, DefaultDirectedGraph<Object, DefaultEdge> directedGraph) {
        return otherQueryHandler.handleLimit(node,directedGraph);
    }
}
