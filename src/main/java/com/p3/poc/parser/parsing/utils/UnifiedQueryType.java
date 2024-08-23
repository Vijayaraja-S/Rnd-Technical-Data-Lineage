package com.p3.poc.parser.parsing.utils;

import io.trino.sql.tree.*;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum UnifiedQueryType {
    BASE_CLASS(QueryBody.class),

    WITH(With.class),
    LIMIT(Limit.class),
    OFFSET(Offset.class),
    ORDER_BY(OrderBy.class),

    EXCEPT(Except.class),
    INTERSECT(Intersect.class),
    SET_OPERATION(SetOperation.class),
    TABLE_SUBQUERY(TableSubquery.class),
    TABLE(Table.class),
    UNION(Union.class),
    VALUES(Values.class),

    QUERY_SPECIFICATION(QuerySpecification.class),

    SELECT(Select.class),
    GROUP_BY(GroupBy.class);


    private final Class<?>[] typeClasses;

    UnifiedQueryType(Class<?>... typeClass) {
        this.typeClasses = typeClass;
    }


    public static UnifiedQueryType getUnifiedQueryType(Object object) {
        return Stream.of(values())
                .filter(type -> Stream.of(type.getTypeClasses())
                        .anyMatch(cls -> cls.equals(object.getClass())))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported type: " + object.getClass().getName()));
    }
}
