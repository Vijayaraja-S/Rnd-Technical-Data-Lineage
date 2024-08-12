package com.p3.poc.parsing.command;

import com.p3.poc.parsing.node.SQLNode;
import com.p3.poc.parsing.node.SQLNodeVisitor;

public class PopulateDetailsCommand implements SQLCommand {
    protected SQLNodeVisitor<Void> visitor;

    public PopulateDetailsCommand(SQLNodeVisitor<Void> visitor) {
        this.visitor = visitor;
    }

    @Override
    public void execute(SQLNode sqlNode) {
        sqlNode.accept(visitor);
    }
}
