package com.p3.poc.parser.bean;

import com.p3.poc.parser.parsing.handler.expression.indentifier.ExpressionTypes;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Data
@Builder
@Setter
@Getter
public class QueryExpressionInfo {
    @Builder.Default
    private List<ExpressionTypes> expressionType = new ArrayList<>();
    @Builder.Default
    private Map<ExpressionTypes,ExpressionDetailInfo> expressionDetails = new LinkedHashMap<>();
}
