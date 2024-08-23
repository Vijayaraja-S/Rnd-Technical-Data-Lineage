package com.p3.poc.parser.bean.from_relation.sub_relation;

import com.p3.poc.parser.bean.from_relation.BaseRelationInfo;
import com.p3.poc.parser.bean.from_relation.identifier.RelationType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AliasedRelationInfo  extends BaseRelationInfo {
    private BaseRelationInfo relationInfoDetails;
    private String aliasName;
     private List<String> columns = new ArrayList<>();

    public static AliasedRelationInfo getBean() {
        AliasedRelationInfo bean = new AliasedRelationInfo();
        bean.setRelationType(RelationType.ALIASED_RELATION);
        return bean;
    }
}


