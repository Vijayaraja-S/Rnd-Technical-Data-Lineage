package com.p3.poc.parser.bean.others;

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
}
