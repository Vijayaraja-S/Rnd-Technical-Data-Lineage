package com.p3.poc.parsing.node.query_node;

import com.p3.poc.parsing.node.SQLNode;
import com.p3.poc.parsing.node.SQLNodeVisitor;

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
