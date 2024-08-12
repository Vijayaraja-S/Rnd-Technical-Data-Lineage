package com.p3.poc.parser.node;

import lombok.Getter;

@Getter
public abstract class SQLNode {
    private final String type;

    protected SQLNode(String type) {
        this.type = type;
    }

    public abstract <R> void accept(SQLNodeVisitor<R> visitor);
}
