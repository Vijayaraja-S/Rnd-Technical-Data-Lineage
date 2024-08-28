package com.p3.poc.parser.bean.query.query_body.query_specification.relation;

import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecDetails;
import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecType;
import com.p3.poc.parser.bean.query.query_body.query_specification.relation.identifier.RelationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRelationInfo extends QuerySpecDetails {
    private RelationType relationType;

    private static BaseRelationInfo getBean(){
        final BaseRelationInfo baseRelationInfo = new BaseRelationInfo();
        baseRelationInfo.setQueryType(QuerySpecType.FROM);
        return baseRelationInfo;
    }
}

