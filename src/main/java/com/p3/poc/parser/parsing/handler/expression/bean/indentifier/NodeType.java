package com.p3.poc.parser.parsing.handler.expression.bean.indentifier;

public enum NodeType {

    SELECT,GROUP_BY,HAVING,ORDER,LIMIT,OFFSET,WHERE

    //FROM AS RELATION
    ,JOIN,TABLE,SUBQUERY,UNION,RELATION_QUERY_SPEC,RELATION_QUERY_BODY,


    //
    NONE
}
