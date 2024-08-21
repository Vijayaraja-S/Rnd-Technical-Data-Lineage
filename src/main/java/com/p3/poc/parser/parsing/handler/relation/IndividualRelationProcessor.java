package com.p3.poc.parser.parsing.handler.relation;

import com.p3.poc.parser.bean.from_relation.sub_bean.AliasedRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_bean.JoinRelationInfo;
import com.p3.poc.parser.bean.from_relation.sub_bean.TableRelationInfo;
import io.trino.sql.tree.AliasedRelation;
import io.trino.sql.tree.Join;
import io.trino.sql.tree.Table;
import lombok.Data;

@Data
public class IndividualRelationProcessor {

    public AliasedRelationInfo processRelation(AliasedRelationInfo relationInfo, AliasedRelation aliasedRelation) {
        return relationInfo;
    }

    public JoinRelationInfo processRelation(JoinRelationInfo relationInfo, Join joinRelation) {
        return relationInfo;
    }

    public TableRelationInfo processRelation(TableRelationInfo relationInfo, Table tableRelation) {
        return relationInfo;
    }

}
