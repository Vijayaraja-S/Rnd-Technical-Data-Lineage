package com.p3.poc.lineage.sunburst_chart.bean.metadata;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TableMetadata implements Serializable {
  private String name;
  private int columnCount;
  @Builder.Default private Long recordCount = 0L;
  @Builder.Default private String sourceName = "";
  @Builder.Default private String description = "";
  private List<ColumnMetadata> columns;
//  @Builder.Default private List<String> relationship = new ArrayList<>();
  @Builder.Default private List<String> partitionOrder = new ArrayList<>();
  @Builder.Default private List<String> sortOrder = new ArrayList<>();
}
