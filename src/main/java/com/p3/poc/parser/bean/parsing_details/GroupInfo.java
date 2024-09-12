package com.p3.poc.parser.bean.parsing_details;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class GroupInfo {
    @Builder.Default  private String tableDetails="";
    @Builder.Default private String columnDetails="";
}
