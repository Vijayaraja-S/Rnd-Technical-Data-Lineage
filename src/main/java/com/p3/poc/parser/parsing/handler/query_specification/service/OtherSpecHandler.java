package com.p3.poc.parser.parsing.handler.query_specification.service;

import io.trino.sql.tree.*;

public interface OtherSpecHandler {
    void processHavingNode(Expression havingValue);
    void processLimitNode(Limit limit);
    void processOffsetNode(Offset offset);
    void processOrderByNode(OrderBy orderBy);
    void processFromNode(Relation relation);
    void processWhereNode(Expression whereValue);

}
