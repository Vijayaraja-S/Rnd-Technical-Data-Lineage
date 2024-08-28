package com.p3.poc.parser.bean.query.query_body.query_specification.others;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecDetails;
import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecType;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HavingQueryInfo  extends QuerySpecDetails {
    private BaseExpressionInfo queryExpressionInfo;

    public static HavingQueryInfo getBean() {
        final HavingQueryInfo havingQueryInfo = new HavingQueryInfo();
        havingQueryInfo.setQueryType(QuerySpecType.HAVING);
        return havingQueryInfo;
    }
}
