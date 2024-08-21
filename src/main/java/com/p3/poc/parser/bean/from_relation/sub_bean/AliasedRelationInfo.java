package com.p3.poc.parser.bean.from_relation.sub_bean;

import com.p3.poc.parser.bean.from_relation.BaseRelationInfoBean;
import com.p3.poc.parser.parsing.handler.relation.identifier.RelationType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class AliasedRelationInfo  extends BaseRelationInfoBean {
    private Class<?> relationInfoDetails;
    private String aliasName;
    @Builder.Default
    private List<String> columns = new ArrayList<>();


    public static AliasedRelationInfo getBean() {
        AliasedRelationInfo bean = new AliasedRelationInfo();
        bean.setRelationType(RelationType.ALIASED_RELATION);
        return bean;
    }
}
