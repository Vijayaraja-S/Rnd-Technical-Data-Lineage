package com.p3.poc.lineage.bean.flow.db_objs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class HavingExpressionInfo {
    private String expressionContent;
    private ConditionType conditionType;
    private String operator;
    private String columnId;
    private String columnName;
    private String left;
    private String rightValue;
    private boolean isMultiRowFunction;
}
