package com.p3.poc.bean.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JoinBean {
    private String joinType;
    private String joinCondition;
    private TableInfo joinedTable;
}
