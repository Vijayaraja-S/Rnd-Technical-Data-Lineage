package com.p3.poc.parser.bean.select;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectColumnInfo {
    private String wholeColumnName;
    private String alias;
    private BaseExpressionInfo queryExpressionInfo;
}
