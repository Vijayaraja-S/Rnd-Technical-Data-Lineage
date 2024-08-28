package com.p3.poc.parser.bean.query.query_body.query_specification.group;

import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecDetails;
import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupQueryInfo extends QuerySpecDetails {
    private boolean isDistinct;
    @Builder.Default private List<BaseGroupElementInfo> groupElementInfos = new ArrayList<>();

    public static GroupQueryInfo getBean(){
        final GroupQueryInfo groupQueryInfo = new GroupQueryInfo();
        groupQueryInfo.setQueryType(QuerySpecType.GROUP_BY);
        return groupQueryInfo;
    }
}
