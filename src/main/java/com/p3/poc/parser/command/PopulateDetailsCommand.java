package com.p3.poc.parser.command;

import com.p3.poc.parser.node.SQLNode;
import com.p3.poc.parser.node.SQLNodeVisitor;

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
