package com.p3.poc.parser.parsing.handler.expression.bean;

import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.OperationType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class HavingExpressionInfo {
    private String expressionContent;
    private OperationType conditionType;
    private String operator;
    private String columnId;
    private String columnName;
    private String left;
    private String rightValue;
    private boolean isMultiRowFunction;
}
