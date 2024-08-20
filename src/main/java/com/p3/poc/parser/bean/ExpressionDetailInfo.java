package com.p3.poc.parser.bean;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class ExpressionDetailInfo {
    // DE - REFERENCE
    private String baseReference;
    private String columnName;
}
