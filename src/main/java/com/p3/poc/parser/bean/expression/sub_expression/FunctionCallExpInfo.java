package com.p3.poc.parser.bean.expression.sub_expression;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.expression.indentifier.ExpressionTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionCallExpInfo extends BaseExpressionInfo {
    private String fullExpression;
    private String functionName;

    public static FunctionCallExpInfo getBean() {
        final FunctionCallExpInfo bean = new FunctionCallExpInfo();
        bean.setExpressionType(ExpressionTypes.FUNCTION_CALL);
        return bean;
    }
}
