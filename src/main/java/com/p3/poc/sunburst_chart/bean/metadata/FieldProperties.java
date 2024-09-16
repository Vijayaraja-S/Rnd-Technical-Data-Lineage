package com.p3.poc.sunburst_chart.bean.metadata;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FieldProperties {
  @Builder.Default private Integer precision = 0;
  @Builder.Default private Integer scale = 0;
  @Builder.Default private Boolean nullable = false;
  @Builder.Default private Boolean autoIncrement = false;
  @Builder.Default private Boolean primaryKey = false;
}
