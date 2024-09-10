package com.p3.poc.lineage.bean.flow.db_objs;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class GroupInfo {
    private String tableDetails;
    private String columnDetails;
}
