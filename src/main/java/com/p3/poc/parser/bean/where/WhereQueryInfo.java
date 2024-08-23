package com.p3.poc.parser.bean.where;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WhereQueryInfo {
    private BaseExpressionInfo queryExpressionInfo;
}
