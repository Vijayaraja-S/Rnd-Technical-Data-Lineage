package com.p3.poc.parser.command;

import io.trino.sql.tree.*;

public interface BaseNodes {

    void process(With with);

    void process(QueryBody queryBody);

    void process(OrderBy orderBy);

    void process(Offset offset);

    void process(Limit limit);

}
