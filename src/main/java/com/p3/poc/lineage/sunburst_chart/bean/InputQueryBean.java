package com.p3.poc.lineage.sunburst_chart.bean;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Data
@Getter
@Setter
public class InputQueryBean {
    private List<InputQueryDetails> inputQueryDetails;

}
