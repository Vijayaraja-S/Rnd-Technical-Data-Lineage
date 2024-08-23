package com.p3.poc.parser.bean.from_relation;

import com.p3.poc.parser.bean.from_relation.identifier.RelationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseRelationInfo {
    private RelationType relationType;
}

