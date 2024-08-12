package com.p3.poc.parser.node.query_node;

import com.p3.poc.parser.node.SQLNode;
import com.p3.poc.parser.node.SQLNodeVisitor;

public class SelectNode extends SQLNode {
    public SelectNode() {
        super("SELECT");
    }

    @Override
    public <R> void accept(SQLNodeVisitor<R> visitor) {
        visitor.visit(this);
    }
}
