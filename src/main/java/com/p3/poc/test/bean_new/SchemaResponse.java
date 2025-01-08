package com.p3.poc.test.bean_new;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SchemaResponse {
    private String schemaId;
    private String schema;
    private List<TableResponse> tables;
}
