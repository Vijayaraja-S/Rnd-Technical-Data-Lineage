package com.p3.poc.parser.visitors;

import com.p3.poc.parser.composite.query_node.*;

public interface SQLNodeVisitor<T> {
    T visit(SelectNode selectNode);
    T visit(CTENode cteNode);
    T visit(JoinNode joinNode);
    T visit(WhereNode whereNode);
    T visit(GroupByNode groupByNode);
    T visit(HavingNode havingNode);
    T visit(WindowFunctionNode windowFunctionNode);
    T visit(FromNode fromNode);
}
