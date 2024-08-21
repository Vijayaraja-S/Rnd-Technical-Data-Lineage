package com.p3.poc.parser.bean.select;

import com.p3.poc.parser.bean.expression.ExpressionDetails;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SelectColumnInfo {
    private String wholeColumnName;
    private String alias;
    @Builder.Default private List<ExpressionDetails> queryExpressionInfo=new ArrayList<>();
}
