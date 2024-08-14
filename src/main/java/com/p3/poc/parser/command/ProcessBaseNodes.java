package com.p3.poc.parser.command;

import io.trino.sql.tree.*;

public class ProcessBaseNodes implements BaseNodes {

    @Override
    public void process(With with) {
        for (Node child : with.getChildren()) {

        }
     }

    @Override
    public void process(QueryBody queryBody) {

    }

    @Override
    public void process(OrderBy orderBy) {

    }

    @Override
    public void process(Offset offset) {

    }

    @Override
    public void process(Limit limit) {

    }
}
