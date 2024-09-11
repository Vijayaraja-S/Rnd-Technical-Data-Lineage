package com.p3.poc.parser.parsing.handler.expression;

import com.p3.poc.lineage.bean.flow.db_objs.*;
import com.p3.poc.parser.parsing.handler.expression.service.*;
import com.p3.poc.parser.parsing.handler.expression.service_impl.HavingProcessor;
import com.p3.poc.parser.parsing.handler.expression.service_impl.JoinProcessor;
import com.p3.poc.parser.parsing.handler.expression.service_impl.OrderByProcessor;
import com.p3.poc.parser.parsing.handler.expression.service_impl.WhereProcessor;
import io.trino.sql.tree.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpressionProcessor extends ExpressionHelper {
    private final ExpressionType type;
    private final Object commonBean;

    public ExpressionProcessor(ExpressionType type, Object commonBean) {
        this.type = type;
        this.commonBean = commonBean;
    }

    public Object processExpression(DereferenceExpression expression) {
        if (type.equals(ExpressionType.WHERE)) {
            final Dereference whereProcessor = new WhereProcessor(commonBean);
            whereProcessor.processDereferenceExpression(expression);
        } else if (type.equals(ExpressionType.ORDER)) {
            final Dereference orderByProcessor = new OrderByProcessor(commonBean);
            orderByProcessor.processDereferenceExpression(expression);
        } else {
            return getColumnDetails(expression);
        }
        return null;
    }


    public Object processExpression(Identifier identifier) {
        final ColumnDetails columnDetails = ColumnDetails.builder().build();
        columnDetails.setColumnName(identifier.getValue());
        return columnDetails;
    }

    public Object processExpression(ComparisonExpression comparisonExpression) {
        if (type.equals(ExpressionType.JOIN)) {
            final Comparison joinProcessor = new JoinProcessor();
            return joinProcessor.processComparisonExpression(comparisonExpression);
        } else if (type.equals(ExpressionType.WHERE)) {
            final Comparison whereProcessor = new WhereProcessor(commonBean);
            return whereProcessor.processComparisonExpression(comparisonExpression);
        }else if (type.equals(ExpressionType.HAVING)) {
            final Comparison havingProcessor = new HavingProcessor(commonBean);
            return havingProcessor.processComparisonExpression(comparisonExpression);
        }
        return null;
    }

    public Object processExpression(LogicalExpression logicalExp) {
        if (type.equals(ExpressionType.WHERE)) {
            final Logical whereProcessor = new WhereProcessor(commonBean);
            whereProcessor.processLogicalExpression(logicalExp);
        }
        return null;
    }

    public Object processExpression(LongLiteral longLiteral) {
        if (type.equals(ExpressionType.WHERE) && commonBean instanceof WhereExpressionInfo whereExpressionInfo) {
            whereExpressionInfo.setRightValue(String.valueOf(longLiteral.getValue()));
        } else if (type.equals(ExpressionType.HAVING) && commonBean instanceof HavingExpressionInfo havingExpressionInfo) {
            havingExpressionInfo.setRightValue(String.valueOf(longLiteral.getValue()));
        } else if (type.equals(ExpressionType.OFFSET)&&commonBean instanceof OffsetInfo offsetInfo) {
            offsetInfo.setOffset(String.valueOf(longLiteral.getValue()));
        } else if (type.equals(ExpressionType.LIMIT)&&commonBean instanceof LimitInfo limitInfo) {
            limitInfo.setLimit(String.valueOf(longLiteral.getValue()));
        }
        return null;
    }

    public Object processExpression(IsNotNullPredicate isNotNullPredicate) {
        if (type.equals(ExpressionType.WHERE)) {
            final IsNotNull whereProcessor = new WhereProcessor(commonBean);
            whereProcessor.processIsNotNullExpression(isNotNullPredicate);
        }
        return null;
    }

    public Object processExpression(BetweenPredicate betweenPredicate) {
        if (type.equals(ExpressionType.WHERE)) {
            final Between whereProcessor = new WhereProcessor(commonBean);
            whereProcessor.processBetweenExpression(betweenPredicate);
        }
        return null;
    }

    public Object processExpression(FunctionCall functionCall) {
        if (type.equals(ExpressionType.HAVING)) {
            final FunctionCallExpression havingProcessor = new HavingProcessor(commonBean);
            havingProcessor.processFunctionExpression(functionCall);
        }
        return null;
    }
}
