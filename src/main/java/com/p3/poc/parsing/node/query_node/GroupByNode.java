package com.p3.poc.parsing.node.query_node;

import com.p3.poc.parsing.node.SQLNode;
import com.p3.poc.parsing.node.SQLNodeVisitor;

public class GroupByNode extends SQLNode {
    private String[] columns;

    public GroupByNode(String... columns) {
        super("GROUPBY");
        this.columns = columns;
    }

    public String[] getColumns() {
        return columns;
    }

    @Override
    public <R> void accept(SQLNodeVisitor<R> visitor) {
        visitor.visit(this);
    }
}
