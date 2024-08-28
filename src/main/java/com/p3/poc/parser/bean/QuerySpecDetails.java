package com.p3.poc.parser.bean;

import com.p3.poc.parser.bean.query.query_body.query_specification.others.*;
import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.relation.BaseRelationInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.group.GroupQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.select.SelectQueryInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuerySpecDetails {
    private SelectQueryInfo selectColumns ;
    private BaseRelationInfo fromRelationInfo;
    private HavingQueryInfo havingQueryInfo;
    private WhereQueryInfo whereQueryInfo;
    private GroupQueryInfo groupQueryInfo;
    private LimitInfo limitInfo;
    private OrderByInfo orderByInfo;
    private WindowsInfo windowsInfo;
    private OffsetInfo offsetInfo;
}
