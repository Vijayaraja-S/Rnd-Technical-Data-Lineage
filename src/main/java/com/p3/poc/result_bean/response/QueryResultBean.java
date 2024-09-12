package com.p3.poc.result_bean.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class QueryResultBean {
    private List<ApplicationInfo> applicationList;
    private List<OutputBean> output;
    private List<ExpressionBean> expressionBeanList;
}
