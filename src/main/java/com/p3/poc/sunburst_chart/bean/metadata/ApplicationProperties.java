package com.p3.poc.sunburst_chart.bean.metadata;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApplicationProperties {
  @Builder.Default private String system = "PEOPLESOFT";
  @Builder.Default private List<String> subSystem = List.of("HRMS", "FINANCE");
  @Builder.Default private String version = "1.0";
  @Builder.Default private List<String> cannedReports = List.of("CODE1", "CODE2", "CODE3");
}
