package com.p3.poc.parsing.node.query_node;

import com.p3.poc.parsing.node.SQLNode;
import com.p3.poc.parsing.node.SQLNodeVisitor;
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
