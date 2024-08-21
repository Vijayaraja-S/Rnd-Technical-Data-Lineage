package com.p3.poc.parser.bean.expression;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class DeReferenceExpressionDetailInfo {
    private String baseReference;
    private String columnName;
}
