package com.p3.poc.parser.parsing.handler.query.service;

import com.p3.poc.parser.bean.query.query_body.BaseQueryBodyInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import io.trino.sql.tree.Node;
import io.trino.sql.tree.QueryBody;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public interface OtherQueryHandler {
    BaseQueryBodyInfo handleQueryBody(QueryBody queryBody, DefaultDirectedGraph<Object, DefaultEdge> directedGraph);
    OrderByInfo handleOrderBy(Node node, DefaultDirectedGraph<Object, DefaultEdge> directedGraph);
    OffsetInfo handleOffset(Node node, DefaultDirectedGraph<Object, DefaultEdge> directedGraph);
    LimitInfo handleLimit(Node node, DefaultDirectedGraph<Object, DefaultEdge> directedGraph);
}
