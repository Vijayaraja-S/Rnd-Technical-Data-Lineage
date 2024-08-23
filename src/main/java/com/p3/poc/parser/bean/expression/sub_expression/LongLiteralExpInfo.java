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
public class LongLiteralExpInfo extends BaseExpressionInfo {
    private Long value;

    public static LongLiteralExpInfo getBean() {
        final LongLiteralExpInfo bean = new LongLiteralExpInfo();
        bean.setExpressionType(ExpressionTypes.LONG_LITERAL);
        return bean;
    }
}
