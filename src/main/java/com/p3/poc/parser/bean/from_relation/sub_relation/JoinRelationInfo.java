package com.p3.poc.parser.bean.from_relation.sub_relation;

import com.p3.poc.parser.bean.from_relation.BaseRelationInfo;
import com.p3.poc.parser.bean.from_relation.identifier.RelationType;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinRelationInfo extends BaseRelationInfo {
    // need to add
    private String tableName;


    public static JoinRelationInfo getBean() {
        final JoinRelationInfo joinRelationInfo = new JoinRelationInfo();
        joinRelationInfo.setRelationType(RelationType.JOIN);
        return joinRelationInfo;
    }
}
