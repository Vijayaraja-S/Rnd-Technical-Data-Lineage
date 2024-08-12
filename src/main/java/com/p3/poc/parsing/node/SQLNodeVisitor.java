package com.p3.poc.parsing.node;

import com.p3.poc.parsing.node.query_node.*;

public interface SQLNodeVisitor<R> {
    R visit(SelectNode selectNode);
    R visit(CTENode cteNode);
    R visit(JoinNode joinNode);
    R visit(WhereNode whereNode);
    R visit(GroupByNode groupByNode);
    R visit(HavingNode havingNode);
    R visit(WindowFunctionNode windowFunctionNode);
    R visit(FromNode fromNode);
}
