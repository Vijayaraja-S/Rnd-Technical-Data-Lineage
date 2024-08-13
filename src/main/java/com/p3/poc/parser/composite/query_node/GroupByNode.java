package com.p3.poc.parser.composite.query_node;

import com.p3.poc.parser.composite.SQLNode;
import com.p3.poc.parser.visitors.SQLNodeVisitor;

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
