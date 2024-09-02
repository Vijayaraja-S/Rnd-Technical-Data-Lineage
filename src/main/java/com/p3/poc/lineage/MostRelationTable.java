package com.p3.poc.lineage;

import lombok.*;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MostRelationTable{
    private String schema;
    private String table;
}
