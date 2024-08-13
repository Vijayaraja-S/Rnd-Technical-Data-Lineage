package com.p3.poc.parser.command;

import com.p3.poc.parser.composite.SQLNode;
import com.p3.poc.parser.visitors.SQLNodeVisitor;

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
