package com.p3.poc.lineage.parser.bean.parsing_details;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
public class SchemaDetails {
    private String id;
    private String schemaName;
}
