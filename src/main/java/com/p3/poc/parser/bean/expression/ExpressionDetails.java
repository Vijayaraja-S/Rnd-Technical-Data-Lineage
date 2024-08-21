package com.p3.poc.parser.bean.expression;

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
public class ExpressionDetails {
    @Builder.Default
    private List<ExpressionTypes> expressionType = new ArrayList<>();
    // 70 expression types is there
    private DeReferenceExpressionDetailInfo deReferenceExpressionDetailInfo;
}
