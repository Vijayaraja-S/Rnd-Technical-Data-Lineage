package com.p3.poc.parser.bean.query.query_body;

import com.p3.poc.parser.bean.relation.BaseRelationInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.group.GroupQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.HavingQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.WhereQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.select.SelectQueryInfo;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuerySpecificationDetails extends BaseQueryBodyInfo {
    private SelectQueryInfo selectQueryInfo;
    private BaseRelationInfo baseRelationInfo;
    private WhereQueryInfo whereQueryInfo;
    private HavingQueryInfo havingQueryInfo;
    private OrderByInfo orderByInfo;
    private LimitInfo limitInfo;
    private OffsetInfo offsetInfo;
    private GroupQueryInfo groupQueryInfo;
}
