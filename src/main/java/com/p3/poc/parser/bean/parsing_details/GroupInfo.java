package com.p3.poc.parser.bean.parsing_details;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class GroupInfo {
    private String tableDetails;
    private String columnDetails;
}
