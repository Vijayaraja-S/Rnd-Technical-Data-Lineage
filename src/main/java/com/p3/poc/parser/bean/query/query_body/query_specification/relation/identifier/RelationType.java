package com.p3.poc.parser.bean.query.query_body.query_specification.relation.identifier;

public enum RelationType {
    ALIASED_RELATION,
    EXCEPT,
    INTERSECT,
    JOIN,
    LATERAL,
    PATTERN_RECOGNITION,
    QUERY_BODY,
    QUERY_SPECIFICATION,
    TABLE,
    TABLE_SUBQUERY,
    TABLE_FUNCTION_INVOCATION,
    SAMPLED_RELATION,
    SET_OPERATION,
    UNION,
    UNNEST,
    VALUES
}
