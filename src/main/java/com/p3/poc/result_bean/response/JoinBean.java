package com.p3.poc.result_bean.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JoinBean {
    private String joinType;
    private String joinCondition;
    private TableInfo joinedTable;
}
