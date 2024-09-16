package com.p3.poc.sunburst_chart.bean.metadata;

import lombok.*;

import java.io.BufferedWriter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MetadataMonitoring {
  private String filePath;
  private BufferedWriter writerBean;
  private String template;
  private Boolean header;
  private int tableCount;
  private int rollNo;
}
