package com.p3.poc.parser.parsing.handler.query_spec;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.select.SelectColumnInfo;
import com.p3.poc.parser.bean.select.SelectQueryInfo;
import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import com.p3.poc.parser.parsing.handler.expression.CommonExpressionHandler;
import io.trino.sql.tree.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Slf4j
@Data
public class SelectHandler implements CommonQueryParser {
    private QueryParsedDetails queryParsedDetails;
    private SelectQueryInfo selectInfo;
    private CommonExpressionHandler commonExpressionHandler;

    public SelectHandler(QueryParsedDetails queryDetails) {
        this.queryParsedDetails = queryDetails;
        this.selectInfo = new SelectQueryInfo();
        this.commonExpressionHandler = new CommonExpressionHandler();
    }

    @Override
    public void processQuery(Object node) {
        final Select selectNode = (Select) node;

        final List<SelectColumnInfo> selectColumnInfoList = selectNode.getSelectItems().stream().map(selectItem -> {
            if (selectItem instanceof SingleColumn singleColumn) {
                return processSingleColumn(singleColumn);
            } else if (selectItem instanceof AllColumns allColumns) {
                return processAllColumn(allColumns);
            } else {
                throw new IllegalArgumentException("Unsupported select item: " + selectItem);
            }
        }).filter(Objects::nonNull).toList();

        selectInfo.setDistinct(selectNode.isDistinct());
        selectInfo.setSelectColumnInfo(selectColumnInfoList);
        queryParsedDetails.setSelectColumns(selectInfo);
    }

    private SelectColumnInfo processSingleColumn(SingleColumn singleColumn) {
        final Optional<Identifier> alias = singleColumn.getAlias();
        final Expression expression = singleColumn.getExpression();

        final SelectColumnInfo selectColumnInfoBean = getSelectColumnInfo();
        selectColumnInfoBean.setWholeColumnName(singleColumn.toString());
        selectColumnInfoBean.setAlias(alias.isPresent() ? String.valueOf(alias.get()) : "");
        final BaseExpressionInfo baseExpressionInfo = commonExpressionHandler.handleExpression(expression);
        selectColumnInfoBean.setQueryExpressionInfo(baseExpressionInfo);

        return selectColumnInfoBean;
    }

    private SelectColumnInfo processAllColumn(AllColumns allColumns) {
        return null;
    }

    private SelectColumnInfo getSelectColumnInfo() {
        return SelectColumnInfo.builder().build();
    }


}
