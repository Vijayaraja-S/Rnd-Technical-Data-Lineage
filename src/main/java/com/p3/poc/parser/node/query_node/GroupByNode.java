package com.p3.poc.parser.node.query_node;

import com.p3.poc.parser.node.SQLNode;
import com.p3.poc.parser.node.SQLNodeVisitor;

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
