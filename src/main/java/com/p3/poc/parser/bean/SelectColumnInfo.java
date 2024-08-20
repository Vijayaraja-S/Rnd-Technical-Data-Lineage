package com.p3.poc.parser.bean;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SelectColumnInfo {
    private String wholeColumnName;
    private String alias;
    @Builder.Default private List<QueryExpressionInfo> queryExpressionInfo=new ArrayList<>();
}
