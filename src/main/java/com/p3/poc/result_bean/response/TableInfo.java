package com.p3.poc.result_bean.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class TableInfo {
    @Builder.Default private String tableId = UUID.randomUUID().toString();
    private String tableName;
    private String alias;
    private List<ColumnInfo> columns;
    private List<JoinBean> joins;
}
