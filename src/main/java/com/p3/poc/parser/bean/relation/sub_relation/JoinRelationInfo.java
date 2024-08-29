package com.p3.poc.parser.bean.relation.sub_relation;

import com.p3.poc.parser.bean.relation.BaseRelationInfo;
import com.p3.poc.parser.bean.relation.identifier.RelationType;
import com.p3.poc.parser.bean.relation.sub_relation.join_criteria.JoinCriteriaInfo;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinRelationInfo extends BaseRelationInfo {
    private String  type;
    private BaseRelationInfo left;
    private BaseRelationInfo right;
    private JoinCriteriaInfo joinCriteria;

    public static JoinRelationInfo getBean() {
        final JoinRelationInfo joinRelationInfo = new JoinRelationInfo();
        joinRelationInfo.setRelationType(RelationType.JOIN);
        return joinRelationInfo;
    }
}
