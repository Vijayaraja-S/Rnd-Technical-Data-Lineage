package com.p3.poc.lineage.parser.parsing.handler.relation;

import com.p3.poc.lineage.parser.bean.parsing_details.TableDetails;
import io.trino.sql.tree.AliasedRelation;
import io.trino.sql.tree.Join;
import io.trino.sql.tree.Relation;
import io.trino.sql.tree.Table;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
@Data
public class RelationHandler {
    private RelationProcessor relationProcessor;

    private final Map<Class<? extends Relation>, Consumer<Relation>> handlers;

    @SneakyThrows
    public RelationHandler() {
        this.handlers = new HashMap<>();
        handlers.put(AliasedRelation.class, this::aliasedRelationHandler);
        handlers.put(Join.class, this::joinRelationHandler);
        handlers.put(Table.class, this::tableRelationHandler);
    }

    public void handleRelation(Relation relation, TableDetails table) {
        this.relationProcessor = new RelationProcessor(table);
        final Consumer<Relation> handler = handlers.getOrDefault(relation.getClass(),this::processUnknownRelation);

        if (handler != null) {
            handler.accept(relation);
        } else {
            processUnknownRelation(relation);
        }
    }

     private void joinRelationHandler(Relation relation) {
        final Join joinRelation = (Join) relation;
        relationProcessor.processJoin(joinRelation);
    }

    private void aliasedRelationHandler(Relation relation) {
        final AliasedRelation aliasedRelation = (AliasedRelation) relation;
        relationProcessor.processAlias(aliasedRelation);
    }

    private void tableRelationHandler(Relation relation) {
        final Table table = (Table) relation;
        relationProcessor.processTable(table);
    }

    private void processUnknownRelation(Relation relation) {
        log.warn("Unknown relation type: {}", relation.getClass().getSimpleName());
    }
}
