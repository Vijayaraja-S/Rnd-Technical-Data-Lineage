package com.p3.poc.lineage.bean.flow.db_objs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class WhereExpressionInfo {
    private String expressionContent;
    private ConditionType conditionType;
    private String columnId;
    private String columnName;
    private String rightValue;

    //BETWEEN
    private String min;
    private String max;


}
