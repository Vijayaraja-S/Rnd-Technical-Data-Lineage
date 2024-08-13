package com.p3.poc.parser.composite.query_node;

import com.p3.poc.parser.composite.SQLNode;
import com.p3.poc.parser.visitors.SQLNodeVisitor;

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
