package com.p3.poc.parser.parsing.utils;

import com.p3.poc.bean.QuerySpecDetails;

public class QueryDetailsSingleton {

    private static QuerySpecDetails instance;

    private QueryDetailsSingleton() {
        // Private constructor to prevent instantiation
    }

    public static QuerySpecDetails getInstance() {
        if (instance == null) {
            instance = QuerySpecDetails.builder().build();
        }
        return instance;
    }
}
