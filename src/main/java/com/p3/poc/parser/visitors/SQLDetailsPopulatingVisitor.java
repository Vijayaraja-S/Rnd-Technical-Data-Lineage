package com.p3.poc.parser.visitors;

import com.p3.poc.parser.bean.SQLQueryDetails;
import com.p3.poc.parser.composite.query_node.*;
import lombok.Getter;

@Getter
public class SQLDetailsPopulatingVisitor implements SQLNodeVisitor<Void> {
    private final SQLQueryDetails sqlQueryDetails;

    public SQLDetailsPopulatingVisitor() {
        this.sqlQueryDetails = new SQLQueryDetails();
    }

    @Override
    public Void visit(SelectNode selectNode) {
        return null;
    }

    @Override
    public Void visit(CTENode cteNode) {
        return null;
    }

    @Override
    public Void visit(JoinNode joinNode) {
        return null;
    }

    @Override
    public Void visit(WhereNode whereNode) {
        return null;
    }

    @Override
    public Void visit(GroupByNode groupByNode) {
        return null;
    }

    @Override
    public Void visit(HavingNode havingNode) {
        return null;
    }

    @Override
    public Void visit(WindowFunctionNode windowFunctionNode) {
        return null;
    }

    @Override
    public Void visit(FromNode fromNode) {
        return null;
    }
}
