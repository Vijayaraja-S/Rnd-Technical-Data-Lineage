package com.p3.poc.sunburst_chart.bean.metadata;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ColumnMetadata implements Serializable {
  private String name;
  private Integer ordinal;
  private String dataType;
  @Builder.Default private Integer length = 0;
  @Builder.Default private String description = "";
  @Builder.Default private Boolean sorted = false;
  @Builder.Default private Boolean partitioned = false;
  @Builder.Default private Boolean bucketing = false;
  @Builder.Default private Boolean encrypt = false;
  private Source source;
  private FieldProperties fieldProperties;
  private DataProperties dataProperties;
}
