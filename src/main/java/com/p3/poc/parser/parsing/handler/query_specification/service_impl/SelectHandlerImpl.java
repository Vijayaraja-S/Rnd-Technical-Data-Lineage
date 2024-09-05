package com.p3.poc.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.select.SelectColumnInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.select.SelectQueryInfo;
import com.p3.poc.parser.parsing.handler.expression.ExpressionHandler;
import com.p3.poc.parser.parsing.handler.query_specification.service.SelectNodeHandler;
import io.trino.sql.tree.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SelectHandlerImpl implements SelectNodeHandler {

    private final ExpressionHandler commonExpressionHandler;

    public SelectHandlerImpl() {
        this.commonExpressionHandler = new ExpressionHandler();
    }

    @Override
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
        return bean;
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
}
