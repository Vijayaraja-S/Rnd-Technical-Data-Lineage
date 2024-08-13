package com.p3.poc.parser.composite.query_node;

import com.p3.poc.parser.composite.SQLNode;
import com.p3.poc.parser.visitors.SQLNodeVisitor;
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
    public <T> void accept(SQLNodeVisitor<T> visitor) {
        visitor.visit(this);
    }
}
