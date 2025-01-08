package com.p3.poc.lineage.bean.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class ApplicationInfo {
    @Builder.Default private String appId = UUID.randomUUID().toString();
    private String appName;
    private List<SchemaInfo> schemaInfoList;
}
