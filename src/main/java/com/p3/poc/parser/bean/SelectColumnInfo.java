package com.p3.poc.parser.bean;

import lombok.Data;

@Data
public class SelectColumnInfo {
    private String columnName;
    private String columnType;
    private String alias;
    private boolean isHavingExpression;
    private QueryExpressionInfo queryExpressionInfo;
}
