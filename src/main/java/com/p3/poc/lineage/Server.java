package com.p3.poc.lineage;

import lombok.*;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Server {
    private String name;
    private String dbVendor;
    private boolean supportsCatalogs;
    private boolean supportsSchemas;
    private List<Database> databases;
}
