package com.p3.poc.parser.bean.query.query_body.query_specification.relation.sub_relation.join_criteria;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Data
@Builder
public class JoinCriteriaInfo {
    private String joinCriteriaType;
    private BaseExpressionInfo leftExpression;
    private List<String> columns;
}
