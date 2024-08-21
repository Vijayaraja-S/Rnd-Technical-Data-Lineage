package com.p3.poc.parser.bean.from_relation.sub_bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JoinRelationInfo {
    private String tableName;


    public static JoinRelationInfo getBean() {
        return JoinRelationInfo.builder().build();
    }
}
