package com.p3.poc.parser.bean.from_relation.sub_bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableRelationInfo {
    private String tableName;
    private String schemaName;

    public static TableRelationInfo getBean() {
        return TableRelationInfo.builder().build();
    }
}
