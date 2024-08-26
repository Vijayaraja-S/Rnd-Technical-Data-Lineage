package com.p3.poc.parser.bean.from_relation.sub_relation;

import com.p3.poc.parser.bean.from_relation.BaseRelationInfo;
import com.p3.poc.parser.bean.from_relation.identifier.RelationType;
import com.p3.poc.parser.bean.from_relation.sub_relation.join_criteria.JoinCriteriaInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
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
