package com.p3.poc.lineage.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.lineage.parser.bean.parsing_details.TableDetails;
import com.p3.poc.lineage.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import com.p3.poc.lineage.parser.parsing.handler.relation.RelationHandler;
import io.trino.sql.tree.Relation;

public class FromHandler extends AbstractQuerySpecHandler {
    private final RelationHandler relationHandler;
    private final Relation relation;

    public FromHandler(Relation node) {
        this.relation = node;
        this.relationHandler = new RelationHandler();
    }

    @Override
    public void process() {
        final TableDetails table = TableDetails.builder().build();
        relationHandler.handleRelation(relation, table);
    }
}
