package com.p3.poc.parser.parsing.handler.relation;

import com.p3.poc.lineage.bean.flow.db_objs.TableDetails;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import io.trino.sql.tree.*;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Slf4j
public class RelationProcessor extends RelationHelper {
    private final TableDetails tableDetails;

    public RelationProcessor(TableDetails table) {
        this.tableDetails = table;
    }

    public void processAlias(AliasedRelation aliasedRelation) {
        final Relation relation = aliasedRelation.getRelation();
        tableDetails.setAliasName(String.valueOf(aliasedRelation.getAlias()));
        processNestedRelation(relation, tableDetails);
    }

    public void processJoin(Join join) {
        final List<Node> children = join.getChildren();
        children.forEach(child -> {
            if (child instanceof Join joinChild) {
                new RelationHandler().handleRelation(joinChild, TableDetails.builder().build());
            }else if (child instanceof Relation relation ) {
                processNestedRelation(relation,TableDetails.builder().build());
            } else if (child instanceof Expression expression) {
                new ExpressionHandler().handleExpression(expression,true);
            }
        });

    }

    public void processTable(Table tableRelation) {
        processTableDetails(tableRelation,tableDetails);
    }

}
