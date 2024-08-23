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
public class DeReferenceExpInfo extends BaseExpressionInfo {
    private String baseReference;
    private String columnName;

    public static DeReferenceExpInfo getBean() {
        final DeReferenceExpInfo bean = new DeReferenceExpInfo();
        bean.setExpressionType(ExpressionTypes.DE_REFERENCE);
        return bean;
    }
}
