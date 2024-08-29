package com.p3.poc.parser.bean.query.query_body.query_specification.others;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HavingQueryInfo{
    private BaseExpressionInfo queryExpressionInfo;

    public static HavingQueryInfo getBean() {
        return new HavingQueryInfo();
    }
}
