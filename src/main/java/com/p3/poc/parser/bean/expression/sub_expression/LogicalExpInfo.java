package com.p3.poc.parser.bean.expression.sub_expression;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.expression.indentifier.ExpressionTypes;
import io.trino.sql.tree.LogicalExpression.Operator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogicalExpInfo extends BaseExpressionInfo {
    private Operator operator;
    private String fullExpression;
    private List<BaseExpressionInfo> baseExpressionInfoList;

    public static LogicalExpInfo getBean() {
        final LogicalExpInfo bean = new LogicalExpInfo();
        bean.setExpressionType(ExpressionTypes.LOGICAL);
        return bean;
    }
}
