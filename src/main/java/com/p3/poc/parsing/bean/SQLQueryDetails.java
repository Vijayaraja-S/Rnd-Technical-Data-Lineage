package com.p3.poc.parsing.bean;

import lombok.Data;

import java.util.List;

@Data
public class SQLQueryDetails {
    private List<String> selectedColumns;
    private List<String> tables;
    private List<String> aliases;
    private List<String> schemas;
    private List<String> joinConditions;
    private List<String> whereConditions;
    private List<String> groupByColumns;
}
