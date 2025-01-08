package com.p3.poc.lineage.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.lineage.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.lineage.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.lineage.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import io.trino.sql.tree.Expression;

public class WhereHandler extends AbstractQuerySpecHandler {
    private final ExpressionHandler expressionHandler;
    private final Expression whereExpression;

    public WhereHandler(Expression node) {
        this.whereExpression =  node;
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public void process() {
        expressionHandler.handleExpression(whereExpression, NodeType.WHERE,null);
    }
}
