package com.p3.poc.parser.parsing.utils;

import com.p3.poc.parser.bean.QueryParsedDetails;

public class QueryDetailsSingleton {

    private static QueryParsedDetails instance;

    private QueryDetailsSingleton() {
        // Private constructor to prevent instantiation
    }

    public static QueryParsedDetails getInstance() {
        if (instance == null) {
            instance = new QueryParsedDetails();
        }
        return instance;
    }
}
