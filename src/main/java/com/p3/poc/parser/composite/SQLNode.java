package com.p3.poc.parser.composite;

import com.p3.poc.parser.visitors.SQLNodeVisitor;
import lombok.Getter;

@Getter
public abstract class SQLNode {
    private final String type;

    protected SQLNode(String type) {
        this.type = type;
    }

    public abstract <R> void accept(SQLNodeVisitor<R> visitor);
}
