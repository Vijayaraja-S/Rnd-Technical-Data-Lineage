package com.p3.poc.parser.node.query_node;

import com.p3.poc.parser.node.SQLNode;
import com.p3.poc.parser.node.SQLNodeVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class FromNode extends SQLNode {
    private final List<String> tables; // Stores table names

    public FromNode(List<String> tables) {
        super("FROM");
        this.tables = tables;
    }

    @Override
    public <R> void accept(SQLNodeVisitor<R> visitor) {
        visitor.visit(this);
    }
}
