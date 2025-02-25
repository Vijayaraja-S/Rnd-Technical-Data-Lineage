package com.p3.poc.bean.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OutputBean {
    private String columnName;
    private String alias;
    private String value;
}
