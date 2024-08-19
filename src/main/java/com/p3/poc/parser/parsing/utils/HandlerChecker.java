package com.p3.poc.parser.parsing.utils;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.parsing.handler.BaseClassHandler;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import com.p3.poc.parser.parsing.handler.query.*;
import com.p3.poc.parser.parsing.handler.query_body.*;
import com.p3.poc.parser.parsing.handler.query_spec.SelectHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public final class HandlerChecker {

    private static final QueryParsedDetails queryDetails = QueryDetailsSingleton.getInstance();

    private HandlerChecker(){
        // Private constructor to prevent instantiation
    }

    public static CommonQueryParser getHandler(Object input) {
        UnifiedQueryType type = UnifiedQueryType.getUnifiedQueryType(input);

        return switch (type) {
            case BASE_CLASS -> new BaseClassHandler(queryDetails);

            // QUERY
            case WITH -> new WithHandler(queryDetails);
            case LIMIT -> new LimitHandler(queryDetails);
            case OFFSET -> new OffsetHandler(queryDetails);
            case ORDER_BY -> new OrderByHandler(queryDetails);

            // QUERY BODY
            case EXCEPT -> new ExceptHandler(queryDetails);
            case INTERSECT -> new IntersectHandler(queryDetails);
            case SET_OPERATION -> new SetOperationHandler(queryDetails);
            case TABLE_SUBQUERY -> new TableSubQueryHandler(queryDetails);
            case TABLE -> new TableHandler(queryDetails);
            case UNION -> new UnionHandler(queryDetails);
            case VALUES -> new ValuesHandler(queryDetails);

            // QUERY SPEC
            case SELECT -> new SelectHandler(queryDetails);

            // RELATION
            case ALIAS_RELATION -> null;
            case JOIN -> null;
            case LATERAL -> null;
            case PATTERN_RECOGNITION_RELATION -> null;
            case SAMPLED_RELATION -> null;
            case TABLE_FUNCTION_INVOCATION -> null;
        };
    }
}
