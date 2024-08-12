package com.p3.poc.parsing.node.query_node;

import com.p3.poc.parsing.node.SQLNode;
import com.p3.poc.parsing.node.SQLNodeVisitor;

import java.util.List;

public class FromNode extends SQLNode {
    private List<String> tables; // Stores table names

    public FromNode(List<String> tables) {
        super("FROM");
        this.tables = tables;
    }

    public List<String> getTables() {
        return tables;
    }

    @Override
    public <R> void accept(SQLNodeVisitor<R> visitor) {
        visitor.visit(this);
    }
}
