package com.p3.poc.parser.node.query_node;

import com.p3.poc.parser.node.SQLNode;
import com.p3.poc.parser.node.SQLNodeVisitor;
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
