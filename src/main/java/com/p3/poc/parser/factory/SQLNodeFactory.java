package com.p3.poc.parser.factory;

import com.p3.poc.parser.composite.SQLNode;
import com.p3.poc.parser.composite.query_node.*;


public class SQLNodeFactory {
    public SQLNode createNode(String nodeType, String ...params) {
        switch (nodeType) {
            case "SELECT":
                return new SelectNode();
//            case "FROM":
//                return new FromNode((List<String>) params[0]);
//            case "WHERE":
//                return new WhereNode((String) params[0]);
//            case "GROUP BY":
//                return new GroupByNode((List<String>) params[0]);
//            case "JOIN":
//                return new JoinNode((String) params[0], (String) params[1], (String) params[2]);
//            case "CTE":
//                return new CTENode((String) params[0], (SQLNode) params[1]);
            default:
                throw new IllegalArgumentException("Unknown node type: " + nodeType);
        }
    }

    public static SQLNodeFactory getFactory() {
        return new SQLNodeFactory();
    }
}
