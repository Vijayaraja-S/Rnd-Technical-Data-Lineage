package com.p3.poc.parser.bean.with;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class WithQueryDetails {
    private UUID id;
    private String name;
    private boolean hasNestedWith;
    private List<String> columns;
}
