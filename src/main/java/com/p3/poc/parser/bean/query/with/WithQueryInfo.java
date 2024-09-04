package com.p3.poc.parser.bean.query.with;

import com.p3.poc.parser.bean.query.BaseQueryInfo;
import io.trino.sql.tree.Identifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder

@NoArgsConstructor
@AllArgsConstructor
public class WithQueryInfo {
    private String beanId;
    private String name;
    private BaseQueryInfo query;
    private List<Identifier> columnNames;
    private String withReferenceId;
}
