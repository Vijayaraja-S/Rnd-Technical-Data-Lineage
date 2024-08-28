package com.p3.poc.parser.bean.query.query_body.query_specification.order_by;

import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecDetails;
import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecType;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderByInfo extends QuerySpecDetails {
    private List<SortInfo> sortInfos;

    private static OrderByInfo getBean() {
        final OrderByInfo orderByInfo = new OrderByInfo();
        orderByInfo.setQueryType(QuerySpecType.ORDER_BY);
        return orderByInfo;
    }
}
