package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.lineage.bean.flow.db_objs.TableDetails;
import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.OrderByInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.order_by.SortInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.HavingQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.WhereQueryInfo;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.query_specification.service.OtherSpecHandler;
import com.p3.poc.parser.parsing.handler.relation.RelationHandler;
import io.trino.sql.tree.*;

import java.util.ArrayList;
import java.util.List;

public class OtherQuerySpecImpl implements OtherSpecHandler {
    private final RelationHandler relationHandler;
    private final ExpressionHandler expressionHandler;

    public OtherQuerySpecImpl() {
        this.relationHandler = new RelationHandler();
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    public void processFromNode(Relation relation) {
        final TableDetails table = TableDetails.builder().build();
        relationHandler.handleRelation(relation, table);
    }


    @Override
    public void processOffsetNode(Offset offset) {
        processExpression(offset.getRowCount());
    }

    @Override
    public void processLimitNode(Limit limit) {

    }

    @Override
    public void processHavingNode(Expression havingValue) {


    }

    @Override
    public void processWhereNode(Expression whereValue) {
    }

    @Override
    public void processOrderByNode(OrderBy orderBy) {
        final OrderByInfo orderByInfo = OrderByInfo.getBean();
        final List<SortInfo> sortInfos = new ArrayList<>();
        orderBy.getSortItems().forEach(sortItem ->
                {

                }
        );
        orderByInfo.setSortInfos(sortInfos);
    }

    private void processExpression(Expression havingValue) {
         expressionHandler.handleExpression(havingValue,false);
    }
}
