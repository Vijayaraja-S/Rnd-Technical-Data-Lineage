package com.p3.poc.parser.parsing.handler.expression;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.lineage.bean.flow.db_objs.JoinDetailsInfo;
import com.p3.poc.parser.bean.GlobalCollector;
import io.trino.sql.tree.*;

import java.util.Map;
import java.util.Optional;

public class ExpressionProcessor {
    private final ExpressionHandler expressionHandler;
    private final boolean isJoin;
    public ExpressionProcessor(boolean isJoin) {
        this.isJoin = isJoin;
        this.expressionHandler = new ExpressionHandler();

    }

    public ColumnDetails processExpression(DereferenceExpression expression) {
        final ColumnDetails columnDetails = ColumnDetails.builder().build();
        final Optional<Identifier> field = expression.getField();
        final Expression base = expression.getBase();
        if (base instanceof Identifier identifier) {
            columnDetails.setColumnSource(identifier.getValue());
        }
        columnDetails.setColumnName(field.isPresent() ? field.get().toString() : "");
        return columnDetails;
    }



    public ColumnDetails processExpression(ComparisonExpression comparisonExpression) {
        if (isJoin) {
            final ColumnDetails right= getColumnDetails(comparisonExpression.getRight());
            final ColumnDetails left = getColumnDetails(comparisonExpression.getLeft());
            expressionHandler.saveColumDetails(right);
            expressionHandler.saveColumDetails(left);
            final Map<String, JoinDetailsInfo> joinDetailsMap = GlobalCollector.getInstance().getJoinDetailsMap();

            final JoinDetailsInfo detailsInfo = JoinDetailsInfo.builder()
                    .id("j:" + joinDetailsMap.size() + 1)
                    .joinEquation(comparisonExpression.toString())
                    .leftColumn(left)
                    .rightColumn(right)
                    .operationInfo(comparisonExpression.getOperator().getValue())
                    .build();
            joinDetailsMap.put(detailsInfo.getId(), detailsInfo);
        }
        return null;
    }


    public ColumnDetails processExpression(LogicalExpression logicalExp) {
        return null;
    }

    private ColumnDetails getColumnDetails(Expression expression) {
        return expressionHandler.handleExpression(expression, isJoin);
    }
}
