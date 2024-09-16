package com.p3.poc.sunburst_chart.bean.metadata;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreateMetadata {
  private List<SchemaMetadata> schemas;
}
