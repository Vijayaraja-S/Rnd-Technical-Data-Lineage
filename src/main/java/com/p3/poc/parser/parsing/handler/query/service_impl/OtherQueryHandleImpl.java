package com.p3.poc.parser.parsing.handler.query.service_impl;

import com.p3.poc.parser.bean.query.query_body.BaseQueryBodyInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import com.p3.poc.parser.parsing.handler.query.service.OtherQueryHandler;
import com.p3.poc.parser.parsing.handler.query_body.QueryBodyProcessor;
import com.p3.poc.parser.parsing.handler.query_specification.service.OtherSpecHandler;
import com.p3.poc.parser.parsing.handler.query_specification.service_impl.OtherQuerySpecImpl;
import io.trino.sql.tree.*;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class OtherQueryHandleImpl implements OtherQueryHandler {
    private final QueryBodyProcessor queryBodyProcessor;
    private final OtherSpecHandler otherSpecHandler;
    public OtherQueryHandleImpl() {
        this.queryBodyProcessor=new QueryBodyProcessor();
        this.otherSpecHandler=new OtherQuerySpecImpl();
    }

    @Override
    public BaseQueryBodyInfo handleQueryBody(QueryBody queryBody, DefaultDirectedGraph<Object, DefaultEdge> directedGraph) {
        return queryBodyProcessor.handleQueryBody(queryBody);
    }

    @Override
    public OrderByInfo handleOrderBy(Node node, DefaultDirectedGraph<Object, DefaultEdge> directedGraph) {
        final OrderBy orderBy = (OrderBy) node;
        return otherSpecHandler.processOrderByNode(orderBy);
    }

    @Override
    public OffsetInfo handleOffset(Node node, DefaultDirectedGraph<Object, DefaultEdge> directedGraph) {
        final Offset offset = (Offset) node;
        return otherSpecHandler.processOffsetNode(offset);
    }

    @Override
    public LimitInfo handleLimit(Node node, DefaultDirectedGraph<Object, DefaultEdge> directedGraph) {
        final Limit limit = (Limit) node;
        return otherSpecHandler.processLimitNode(limit);
    }
}
