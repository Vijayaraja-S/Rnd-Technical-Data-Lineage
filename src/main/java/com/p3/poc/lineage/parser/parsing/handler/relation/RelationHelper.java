package com.p3.poc.lineage.parser.parsing.handler.relation;

import com.p3.poc.lineage.parser.bean.parsing_details.TableDetails;
import com.p3.poc.lineage.parser.bean.GlobalCollector;
import io.trino.sql.tree.Relation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RelationHelper {

    private final GlobalCollector instance;

    public RelationHelper() {
        this.instance = GlobalCollector.getInstance();
    }

    public void processNestedRelation(Relation relation, TableDetails tableDetails) {
        new RelationHandler().handleRelation(relation, tableDetails);
    }
}
