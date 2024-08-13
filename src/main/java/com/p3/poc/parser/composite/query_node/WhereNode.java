package com.p3.poc.parser.composite.query_node;

import com.p3.poc.parser.composite.SQLNode;
import com.p3.poc.parser.visitors.SQLNodeVisitor;
import lombok.Getter;

@Getter
public class WhereNode extends SQLNode {
    private final String condition;

    public WhereNode(String condition) {
        super("WHERE");
        this.condition = condition;
    }

    @Override
    public <R> void accept(SQLNodeVisitor<R> visitor) {
        visitor.visit(this);
    }
}
