package com.p3.poc.parser.bean.query.query_body.query_specification.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupQueryInfo {
    private boolean isDistinct;
    @Builder.Default private List<BaseGroupElementInfo> groupElementInfos = new ArrayList<>();

    public static GroupQueryInfo getBean(){
        return new GroupQueryInfo();
    }
}
