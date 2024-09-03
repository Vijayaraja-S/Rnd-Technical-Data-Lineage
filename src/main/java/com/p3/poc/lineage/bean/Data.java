package com.p3.poc.lineage.bean;

import com.p3.poc.lineage.bean.flow.SqlQueryFlow;
import com.p3.poc.lineage.bean.summary.Summary;
import com.p3.poc.lineage.bean.graph.Graph;
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
