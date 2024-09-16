package com.p3.poc.sunburst_chart.bean.metadata;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ExportMetadata {
  // asd metadata bean should validate - (build creation)
  private CreateMetadata metadata;
}
