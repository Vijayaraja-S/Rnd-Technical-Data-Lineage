package com.p3.poc.parser.parsing.handler.query_specification.service;

import com.p3.poc.parser.bean.query.query_body.query_specification.group.GroupQueryInfo;
import io.trino.sql.tree.GroupBy;

public interface GroupByNodeHandler {
    GroupQueryInfo processGroupNode(GroupBy groupBy);
}
