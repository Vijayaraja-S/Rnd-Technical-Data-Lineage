package com.p3.poc.parser.parsing.handler.relation;

import com.p3.poc.lineage.bean.flow.db_objs.TableDetails;
import com.p3.poc.parser.bean.relation.BaseRelationInfo;
import com.p3.poc.parser.bean.relation.sub_relation.AliasedRelationInfo;
import com.p3.poc.parser.bean.relation.sub_relation.JoinRelationInfo;
import com.p3.poc.parser.bean.relation.sub_relation.TableRelationInfo;
import io.trino.sql.tree.*;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@Slf4j
public class RelationProcessor extends RelationHelper {
    private final TableDetails tableDetails;
    public RelationProcessor(TableDetails table) {
        this.tableDetails = table;
    }

    public AliasedRelationInfo processAlias(AliasedRelation aliasedRelation) {
        final AliasedRelationInfo relationInfo = AliasedRelationInfo.getBean();
        final Relation relation = aliasedRelation.getRelation();
        getNestedRelationInfo(relation,tableDetails);
        tableDetails.setAliasName(String.valueOf(aliasedRelation.getAlias()));
        return relationInfo;
    }

    public JoinRelationInfo processJoin(Join join) {
        final JoinRelationInfo relationInfo = JoinRelationInfo.getBean();
        relationInfo.setType(String.valueOf(join.getType()));
        relationInfo.setLeft(getNestedRelationInfo(join.getLeft(), tableDetails));
        relationInfo.setRight(getNestedRelationInfo(join.getRight(),tableDetails));

        final Optional<JoinCriteria> criteria = join.getCriteria();
        criteria.ifPresent(joinCriteria -> relationInfo.setJoinCriteria(handleJoinCriteria(joinCriteria)));

        return relationInfo;
    }

    public TableRelationInfo processTable(Table tableRelation) {
        final TableRelationInfo relationInfo = TableRelationInfo.getBean();

        final QualifiedName name = tableRelation.getName();
        final Optional<QualifiedName> schemaName = name.getPrefix();

        tableDetails.setFullName(String.valueOf(name.toString()));
        tableDetails.setName(String.valueOf(name.getSuffix()));
        tableDetails.setSchemaName(schemaName.isPresent()? String.valueOf(schemaName.get()) :"");

        return relationInfo;
    }


}
