package com.p3.poc.parser.parsing.handler.query_specification.service;

import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.HavingQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.WhereQueryInfo;
import com.p3.poc.parser.bean.relation.BaseRelationInfo;
import io.trino.sql.tree.*;

public interface OtherSpecHandler {
    HavingQueryInfo processHavingNode(Expression havingValue);
    LimitInfo processLimitNode(Limit limit);
    OffsetInfo processOffsetNode(Offset offset);
    OrderByInfo processOrderByNode(OrderBy orderBy);
    BaseRelationInfo processFromNode(Relation relation);
    WhereQueryInfo processWhereNode(Expression whereValue);

}
