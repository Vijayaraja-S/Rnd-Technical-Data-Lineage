package com.p3.poc.parser.bean;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class WithQueryObjectDetails {
    private UUID id;
    private String name;
    private boolean hasNestedWith;
    private List<String> columns;
}
