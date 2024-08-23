package com.p3.poc.parser.bean.group;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class GroupQueryInfo {
    private boolean isDistinct;
    @Builder.Default private List<BaseGroupElementInfo> groupElementInfos = new ArrayList<>();
}
