package com.p3.poc.parser.parsing.handler.relation;

import com.p3.poc.parser.bean.parsing_details.ApplicationDetails;
import com.p3.poc.parser.bean.parsing_details.SchemaDetails;
import com.p3.poc.parser.bean.parsing_details.TableDetails;
import com.p3.poc.parser.bean.GlobalCollector;
import io.trino.sql.tree.Identifier;
import io.trino.sql.tree.QualifiedName;
import io.trino.sql.tree.Relation;
import io.trino.sql.tree.Table;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

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
