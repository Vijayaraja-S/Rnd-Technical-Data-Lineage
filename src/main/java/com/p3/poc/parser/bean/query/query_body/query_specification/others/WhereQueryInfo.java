package com.p3.poc.parser.bean.query.query_body.query_specification.others;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecDetails;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WhereQueryInfo extends QuerySpecDetails {
    private BaseExpressionInfo queryExpressionInfo;

    public static WhereQueryInfo getBean() {
        return new WhereQueryInfo();
    }
}
