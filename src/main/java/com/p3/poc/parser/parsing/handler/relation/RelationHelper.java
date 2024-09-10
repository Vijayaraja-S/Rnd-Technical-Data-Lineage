package com.p3.poc.parser.parsing.handler.relation;

import com.p3.poc.lineage.bean.flow.db_objs.TableDetails;
import com.p3.poc.parser.bean.GlobalCollector;
import io.trino.sql.tree.QualifiedName;
import io.trino.sql.tree.Relation;
import io.trino.sql.tree.Table;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class RelationHelper {

    public void processNestedRelation(Relation relation, TableDetails tableDetails) {
        new RelationHandler().handleRelation(relation, tableDetails);
    }


    public void processTableDetails(Table tableRelation, TableDetails tableDetails) {
        final GlobalCollector instance = GlobalCollector.getInstance();

        final QualifiedName name = tableRelation.getName();
        final Optional<QualifiedName> schemaName = name.getPrefix();
        tableDetails.setFullName(String.valueOf(name.toString()));
        tableDetails.setName(String.valueOf(name.getSuffix()));
        tableDetails.setSchemaName(schemaName.isPresent() ? String.valueOf(schemaName.get()) : "");

        final Map<String, TableDetails> tableMap = instance.getTableMap();
        final String aliasName = tableDetails.getAliasName();
        final String key = aliasName.isEmpty() ? tableDetails.getName() : tableDetails.getAliasName();
        tableMap.put(key, tableDetails);
    }
}
