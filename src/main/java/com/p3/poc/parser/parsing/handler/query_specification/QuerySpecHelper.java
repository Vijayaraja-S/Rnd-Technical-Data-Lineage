package com.p3.poc.parser.parsing.handler.query_specification;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.group.BaseGroupElementInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.group.identifier.GroupType;
import com.p3.poc.parser.bean.query.query_body.query_specification.select.SelectColumnInfo;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class QuerySpecHelper {

    private final ExpressionHandler commonExpressionHandler;

    public QuerySpecHelper() {
        this.commonExpressionHandler = new ExpressionHandler();
    }

    SelectColumnInfo processSingleColumn(SingleColumn singleColumn) {
        final Optional<Identifier> alias = singleColumn.getAlias();
        final Expression expression = singleColumn.getExpression();

        final SelectColumnInfo selectColumnInfoBean = getSelectColumnInfo();
        selectColumnInfoBean.setWholeColumnName(singleColumn.toString());
        selectColumnInfoBean.setAlias(alias.isPresent() ? String.valueOf(alias.get()) : "");
        final BaseExpressionInfo baseExpressionInfo = commonExpressionHandler.handleExpression(expression);
        selectColumnInfoBean.setQueryExpressionInfo(baseExpressionInfo);

        return selectColumnInfoBean;
    }

    SelectColumnInfo processAllColumn(AllColumns allColumns) {
        return null;
    }

    private SelectColumnInfo getSelectColumnInfo() {
        return SelectColumnInfo.builder().build();
    }
    BaseGroupElementInfo handlerGroupElements(GroupingElement groupingElement) {
        List<BaseExpressionInfo> expressionInfos = new ArrayList<>();
        final BaseGroupElementInfo bean = BaseGroupElementInfo.getBean();
        setGroupElementType(groupingElement, bean);
        if (groupingElement instanceof GroupingSets) {
            // need to handle

        } else {
            for (Expression expression : groupingElement.getExpressions()) {
                final BaseExpressionInfo baseExpressionInfo = commonExpressionHandler.handleExpression(expression);
                if (baseExpressionInfo != null) {
                    expressionInfos.add(baseExpressionInfo);
                }
            }
            bean.setElementExpressionInfoList(expressionInfos);
        }
        return bean;
    }

    private void setGroupElementType(GroupingElement groupingElement, BaseGroupElementInfo bean) {
        if (groupingElement instanceof Cube) {
            bean.setType(GroupType.CUBE);
        } else if (groupingElement instanceof SimpleGroupBy) {
            bean.setType(GroupType.SIMPLE_GROUP_BY);
        } else if (groupingElement instanceof Rollup) {
            bean.setType(GroupType.ROLL_UP);
        }
    }
}
