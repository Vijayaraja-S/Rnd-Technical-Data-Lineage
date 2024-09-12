package com.p3.poc.result_bean.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SchemaInfo {
    @Builder.Default private String schemaId = UUID.randomUUID().toString();
    private String schemaName;
    private List<TableInfo>tableInfoList;
}
