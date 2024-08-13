package com.p3.poc.parser.composite.query_node;

import com.p3.poc.parser.composite.SQLNode;
import com.p3.poc.parser.visitors.SQLNodeVisitor;

public class SelectNode extends SQLNode {
    public SelectNode() {
        super("SELECT");
    }

    @Override
    public <R> void accept(SQLNodeVisitor<R> visitor) {
        visitor.visit(this);
    }
}
