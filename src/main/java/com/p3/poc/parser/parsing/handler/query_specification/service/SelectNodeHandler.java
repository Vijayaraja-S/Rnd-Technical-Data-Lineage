package com.p3.poc.parser.parsing.handler.query_specification.service;

import com.p3.poc.parser.bean.query.query_body.query_specification.select.SelectQueryInfo;
import io.trino.sql.tree.Select;

public interface SelectNodeHandler {
    SelectQueryInfo processSelectNode(Select selectNode);
}
