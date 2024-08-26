package com.p3.poc.parser.bean.others;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HavingQueryInfo {
    private BaseExpressionInfo queryExpressionInfo;
}
