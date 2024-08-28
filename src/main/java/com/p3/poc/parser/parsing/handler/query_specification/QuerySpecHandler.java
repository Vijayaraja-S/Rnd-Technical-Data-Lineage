package com.p3.poc.parser.parsing.handler.query_specification;

import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecDetails;
import com.p3.poc.parser.bean.query.query_body.query_specification.group.GroupQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.HavingQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.WhereQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.relation.BaseRelationInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.select.SelectQueryInfo;
import io.trino.sql.tree.*;

import java.util.Map;
import java.util.function.Function;

public class QuerySpecHandler {
    private final QuerySpecProcessor queryProcessor;

    private final Map<Class<? extends Node>, Function<Node, ? extends QuerySpecDetails>> handlers;


    public QuerySpecHandler() {
        this.queryProcessor = new QuerySpecProcessor();
        this.handlers = Map.of(
                Select.class, this::handleSelect,
                Relation.class, this::handleFrom,
                GroupBy.class, this::handleGroupBy,
                OrderBy.class, this::handleOffset,
                Offset.class, this::handleOffset,
                Limit.class, this::handleLimit
        );
    }

    @SuppressWarnings("unchecked")
    public <T extends QuerySpecDetails> T handleExpression(Node node) {
        final Function<Node, ? extends QuerySpecDetails> handler = handlers.
                getOrDefault(node.getClass(), this::handleUnknownExpression);
        return (T) handler.apply(node);
    }

    private QuerySpecDetails handleUnknownExpression(Node node) {
        return new QuerySpecDetails();
    }

    private SelectQueryInfo handleSelect(Node node) {
        final Select select = (Select) node;
        return queryProcessor.processSelectNode(select);

    }

    private BaseRelationInfo handleFrom(Node node) {
        final Relation relation = (Relation) node;
        return queryProcessor.processFromNode(relation);
    }

    private GroupQueryInfo handleGroupBy(Node node) {
        final GroupBy groupBy = (GroupBy) node;
        return queryProcessor.processGroupNode(groupBy);
    }

    private OffsetInfo handleOffset(Node node) {
        final Offset offset = (Offset) node;
        return queryProcessor.processOffsetNode(offset);
    }

    private LimitInfo handleLimit(Node node) {
        final Limit limit = (Limit) node;
        return queryProcessor.processLimitNode(limit);
    }
    public HavingQueryInfo handleHaving(Expression expression) {
        return  queryProcessor.processHavingNode(expression);
    }
    public WhereQueryInfo handleWhere(Expression expression) {
        return  queryProcessor.processWhereNode(expression);
    }

}
