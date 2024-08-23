package com.p3.poc.parser.bean;

import com.p3.poc.parser.bean.from_relation.BaseRelationInfo;
import com.p3.poc.parser.bean.group.GroupQueryInfo;
import com.p3.poc.parser.bean.having.HavingQueryInfo;
import com.p3.poc.parser.bean.select.SelectQueryInfo;
import com.p3.poc.parser.bean.where.WhereQueryInfo;
import com.p3.poc.parser.bean.with.WithObjectInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueryParsedDetails {
    private WithObjectInfo withQuery;
    private SelectQueryInfo selectColumns ;
    private BaseRelationInfo fromRelationInfo;
    private HavingQueryInfo havingQueryInfo;
    private WhereQueryInfo whereQueryInfo;
    private GroupQueryInfo groupQueryInfo;
}
