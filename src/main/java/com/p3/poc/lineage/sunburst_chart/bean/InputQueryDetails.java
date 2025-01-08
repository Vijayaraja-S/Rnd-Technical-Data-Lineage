package com.p3.poc.lineage.sunburst_chart.bean;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
public class InputQueryDetails {
    private String queryName;
    private String query;
}
