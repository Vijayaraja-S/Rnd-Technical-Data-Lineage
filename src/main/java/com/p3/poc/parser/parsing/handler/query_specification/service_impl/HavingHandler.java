package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.parser.parsing.handler.expression.bean.indentifier.NodeType;
import com.p3.poc.parser.parsing.handler.expression.service.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.Node;

public class HavingHandler extends AbstractQuerySpecHandler {
    private final ExpressionHandler expressionHandler;
    private final Expression havingExpression;

    public HavingHandler(Expression node) {
        this.havingExpression = node;
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public void process() {
        expressionHandler.handleExpression(havingExpression, NodeType.HAVING,null);
    }
}
