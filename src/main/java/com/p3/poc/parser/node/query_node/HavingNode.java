package com.p3.poc.parser.node.query_node;

import com.p3.poc.parser.node.SQLNode;
import com.p3.poc.parser.node.SQLNodeVisitor;

public class HavingNode extends SQLNode {
    private String condition;

    public HavingNode(String condition) {
        super("HAVING");
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public <R> void accept(SQLNodeVisitor<R> visitor) {
        visitor.visit(this);
    }
}
