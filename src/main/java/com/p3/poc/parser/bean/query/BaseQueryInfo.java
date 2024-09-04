package com.p3.poc.parser.bean.query;

import com.p3.poc.parser.bean.query.query_body.BaseQueryBodyInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import com.p3.poc.parser.bean.query.with.WithInfo;
import lombok.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Builder
public class BaseQueryInfo {
    private WithInfo withInfo;
    private BaseQueryBodyInfo baseQueryBodyInfo;
    private OffsetInfo offsetInfo;
    private LimitInfo limitInfo;
    private OrderByInfo orderByInfo;
}
