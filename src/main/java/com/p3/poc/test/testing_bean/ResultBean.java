package com.p3.poc.test.testing_bean;

import java.util.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
public class ResultBean {
  @Builder.Default private List<TableInformation> tInfos = new LinkedList<>();

  @Builder.Default private List<ColumnInformation> cInfos = new LinkedList<>();

  @Builder.Default private String uniqueId = null;

  @Builder.Default private Boolean isBaseBean = false;
  @Builder.Default private List<ResultBean> resultBeanList = new LinkedList<>();
  @Builder.Default private Boolean hasAllColumnsSelected = false;
}
