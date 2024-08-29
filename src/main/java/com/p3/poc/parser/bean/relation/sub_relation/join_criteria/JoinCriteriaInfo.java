package com.p3.poc.parser.bean.relation.sub_relation.join_criteria;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinCriteriaInfo {
    @Builder.Default private String joinCriteriaType="";
    private BaseExpressionInfo leftExpression;
    @Builder.Default private List<String> columns=new ArrayList<>();
}
