package com.p3.poc.parser.bean.from_relation;

import com.p3.poc.parser.bean.from_relation.sub_bean.AliasedRelationInfo;
import com.p3.poc.parser.parsing.handler.relation.identifier.RelationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
 public abstract class BaseRelationInfoBean {
 private RelationType relationType;

 public static BaseRelationInfoBean getBean() {
    return BaseRelationInfoBean.builder().build();

 }

}


