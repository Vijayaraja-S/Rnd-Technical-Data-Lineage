package com.p3.poc.parser.bean.query.query_body.query_specification.others;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecDetails;
import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecType;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LimitInfo extends QuerySpecDetails {
    private BaseExpressionInfo expression;

    public static LimitInfo getBean() {
        final LimitInfo limitInfo = new LimitInfo();
        limitInfo.setQueryType(QuerySpecType.LIMIT);
        return limitInfo;
    }
}
