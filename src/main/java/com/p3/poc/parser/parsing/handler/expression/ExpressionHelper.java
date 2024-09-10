package com.p3.poc.parser.parsing.handler.expression;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.lineage.bean.flow.db_objs.JoinDetailsInfo;
import com.p3.poc.parser.bean.GlobalCollector;
import io.trino.sql.tree.ComparisonExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpressionHelper {

    public void saveColumDetails(ColumnDetails column) {
        Map<String, List<ColumnDetails>> columnListMap = GlobalCollector.getInstance().getColumnListMap();
        List<ColumnDetails> columnList = columnListMap.computeIfAbsent(column.getColumnSource(), k -> new ArrayList<>());
        int index = columnList.size();
        column.setColumnId(column.getColumnSource() + ":c" + index);
        columnList.add(column);
    }
    public void saveJoinDetailsInfo(ComparisonExpression comparisonExpression, ColumnDetails left, ColumnDetails right) {
        final Map<String, JoinDetailsInfo> joinDetailsMap = GlobalCollector.getInstance().getJoinDetailsMap();

        final JoinDetailsInfo detailsInfo = JoinDetailsInfo.builder()
                .id("j:" + joinDetailsMap.size())
                .joinEquation(comparisonExpression.toString())
                .leftColumn(left)
                .rightColumn(right)
                .operationInfo(comparisonExpression.getOperator().getValue())
                .build();
        joinDetailsMap.put(detailsInfo.getId(), detailsInfo);
    }
}
