package com.p3.poc.parser.bean.expression.sub_expression;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.expression.indentifier.ExpressionTypes;
import io.trino.sql.tree.ComparisonExpression.Operator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComparisonExpInfo extends BaseExpressionInfo {
    private String fullExpression;
    private Operator operator;
    private BaseExpressionInfo left;
    private BaseExpressionInfo right;

    public static ComparisonExpInfo getBean() {
        final ComparisonExpInfo bean = new ComparisonExpInfo();
        bean.setExpressionType(ExpressionTypes.COMPARISON);
        return bean;
    }
}
