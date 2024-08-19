package com.p3.poc.parser.bean;

import lombok.Data;

import java.util.List;

@Data
public class QueryParsedDetails {
    private WithObjectInfo withQuery;
    private List<SelectColumnInfo> selectColumns;
}
