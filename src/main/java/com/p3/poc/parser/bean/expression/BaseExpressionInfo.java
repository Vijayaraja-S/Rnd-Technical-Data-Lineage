package com.p3.poc.parser.bean.expression;

import com.p3.poc.parser.bean.expression.indentifier.ExpressionTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseExpressionInfo {
    private ExpressionTypes expressionType;
}
