package com.p3.poc.parser.parsing.handler.query_specification;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.group.BaseGroupElementInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.group.GroupQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.HavingQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.LimitInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.OffsetInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.others.WhereQueryInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.relation.BaseRelationInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.select.SelectColumnInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.select.SelectQueryInfo;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.relation.RelationHandler;
import io.trino.sql.tree.*;

import java.util.List;
import java.util.Objects;

public class QuerySpecProcessor extends QuerySpecHelper {
    private final RelationHandler commonRelationHandler;
    private final ExpressionHandler commonExpressionHandler;

    public QuerySpecProcessor() {
        super();
        this.commonRelationHandler = new RelationHandler();
        this.commonExpressionHandler = new ExpressionHandler();
    }

    public SelectQueryInfo processSelectNode(Select selectNode) {
        final List<SelectColumnInfo> selectColumnInfoList = selectNode.getSelectItems()
                .stream().
                map(selectItem -> {
                    if (selectItem instanceof SingleColumn singleColumn) {
                        return processSingleColumn(singleColumn);
                    } else if (selectItem instanceof AllColumns allColumns) {
                        return processAllColumn(allColumns);
                    } else {
                        throw new IllegalArgumentException("Unsupported select item: " + selectItem);
                    }
                }).filter(Objects::nonNull).toList();

        final SelectQueryInfo bean = SelectQueryInfo.getBean();
        bean.setDistinct(selectNode.isDistinct());
        bean.setSelectColumnInfo(selectColumnInfoList);
        return null;
    }

    public BaseRelationInfo processFromNode(Relation relation) {
        return commonRelationHandler.handleRelation(relation);
    }

    public GroupQueryInfo processGroupNode(GroupBy groupBy) {
        final List<BaseGroupElementInfo> groupElementInfoList = groupBy.getGroupingElements()
                .stream()
                .map(this::handlerGroupElements)
                .toList();
        final GroupQueryInfo bean = GroupQueryInfo.getBean();
        bean.setDistinct(groupBy.isDistinct());
        bean.setGroupElementInfos(groupElementInfoList);
        return bean;
    }

    public OffsetInfo processOffsetNode(Offset offset) {
        final BaseExpressionInfo expression = processExpression(offset.getRowCount());
        final OffsetInfo offsetInfo = OffsetInfo.getBean();
        offsetInfo.setExpression(expression);
        return offsetInfo;
    }

    public LimitInfo processLimitNode(Limit limit) {
        final BaseExpressionInfo expression = processExpression(limit.getRowCount());
        final LimitInfo limitInfo = LimitInfo.getBean();
        limitInfo.setExpression(expression);
        return limitInfo;
    }

    public HavingQueryInfo processHavingNode(Expression havingValue) {
        final BaseExpressionInfo baseExpressionInfo = processExpression(havingValue);
        final HavingQueryInfo havingQueryInfo = HavingQueryInfo.getBean();
        havingQueryInfo.setQueryExpressionInfo(baseExpressionInfo);
        return havingQueryInfo;
    }

    public WhereQueryInfo processWhereNode(Expression whereValue) {
        final BaseExpressionInfo baseExpressionInfo = processExpression(whereValue);
        final WhereQueryInfo whereQueryInfo = WhereQueryInfo.getBean();
        whereQueryInfo.setQueryExpressionInfo(baseExpressionInfo);
        return whereQueryInfo;
    }

    private BaseExpressionInfo processExpression(Expression havingValue) {
        return commonExpressionHandler.handleExpression(havingValue);
    }
}
