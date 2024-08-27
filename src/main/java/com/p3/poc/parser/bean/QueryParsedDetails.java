package com.p3.poc.parser.bean;

import com.p3.poc.parser.bean.from_relation.BaseRelationInfo;
import com.p3.poc.parser.bean.group.GroupQueryInfo;
import com.p3.poc.parser.bean.others.*;
import com.p3.poc.parser.bean.select.SelectQueryInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueryParsedDetails {
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
