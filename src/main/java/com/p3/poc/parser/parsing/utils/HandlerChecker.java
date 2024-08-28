package com.p3.poc.parser.parsing.utils;

import com.p3.poc.parser.bean.QuerySpecDetails;
import com.p3.poc.parser.parsing.handler.BaseClassHandler;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public final class HandlerChecker {

    private static final QuerySpecDetails queryDetails = QueryDetailsSingleton.getInstance();

    private HandlerChecker() {
        // Private constructor to prevent instantiation
    }

    public static CommonQueryParser getHandler(Object input) {
        UnifiedQueryType type = UnifiedQueryType.getUnifiedQueryType(input);

        return switch (type) {
            case BASE_CLASS -> new BaseClassHandler(queryDetails);
         

            // QUERY BODY
            case WITH -> null;
            case LIMIT -> null;
            case OFFSET -> null;
            case ORDER_BY -> null;


            // QUERY SPEC
            case EXCEPT -> null;
            case INTERSECT -> null;
            case SET_OPERATION -> null;
            case TABLE_SUBQUERY -> null;
            case TABLE -> null;
            case UNION -> null;
            case VALUES -> null;


            case QUERY_SPECIFICATION -> null;
            case SELECT -> null;
            case GROUP_BY -> null;
        };
    }
}
