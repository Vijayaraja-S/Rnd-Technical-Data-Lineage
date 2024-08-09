package com.p3.poc.bean.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class ColumnInfo {
    @Builder.Default private String id = UUID.randomUUID().toString();
    private String name;
    private String alias;
    private String referenceTable;
}
