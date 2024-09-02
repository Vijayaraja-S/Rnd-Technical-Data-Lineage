package com.p3.poc.lineage;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Data{
    private String mode;
    private Summary summary;
    private SqlQueryFlow sqlQueryFlow;
    private Graph graph;
}
