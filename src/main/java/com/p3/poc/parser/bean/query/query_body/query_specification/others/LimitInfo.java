package com.p3.poc.parser.bean.query.query_body.query_specification.others;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LimitInfo {
    private BaseExpressionInfo expression;

    public static LimitInfo getBean() {
        return new LimitInfo();
    }
}
