package com.p3.poc.lineage.sunburst_chart.bean.metadata;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DataProperties {
  @Builder.Default private Boolean analysed = false;
  private Additional additional;
}
