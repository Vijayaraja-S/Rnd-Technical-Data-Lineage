package com.p3.poc.bean.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ExpressionBean {
    private String expressionType;
    private String expressionDetail;
    private TableInfo relatedTable;
    private List<ColumnInfo> relatedColumns;
}
