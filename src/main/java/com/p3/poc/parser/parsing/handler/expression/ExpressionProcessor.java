package com.p3.poc.parser.parsing.handler.expression;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.expression.sub_expression.ComparisonExpInfo;
import com.p3.poc.parser.bean.expression.sub_expression.DeReferenceExpInfo;
import com.p3.poc.parser.bean.expression.sub_expression.FunctionCallExpInfo;
import io.trino.sql.tree.*;

import java.util.Optional;

public class ExpressionProcessor {
    private final ColumnDetails column;
    public ExpressionProcessor(ColumnDetails column) {
        this.column = column;
    }

    public DeReferenceExpInfo processExpression(DeReferenceExpInfo deReferenceExpInfo, DereferenceExpression expression) {
        final Optional<Identifier> field = expression.getField();
        final Expression base = expression.getBase();

        // need to change fo identifier
        if (base instanceof Identifier identifier) {
            deReferenceExpInfo.setBaseReference(identifier.getValue());
            column.setColumnSource(identifier.getValue());
        }
        deReferenceExpInfo.setColumnName(field.isPresent() ? field.get().toString() : "");
        column.setColumnName(field.isPresent() ? field.get().toString() : "");

        return deReferenceExpInfo;
    }


    public FunctionCallExpInfo processExpression(FunctionCallExpInfo functionCallExpInfo, FunctionCall expression) {
        return null;
    }


    public ComparisonExpInfo processExpression(ComparisonExpInfo comparisonExpInfo, ComparisonExpression comparisonExpression) {
        final BaseExpressionInfo left = getBaseExpressionInfo(comparisonExpression.getLeft());
        final BaseExpressionInfo right = getBaseExpressionInfo(comparisonExpression.getRight());

        comparisonExpInfo.setOperator(comparisonExpression.getOperator());
        comparisonExpInfo.setLeft(left);
        comparisonExpInfo.setRight(right);

        return comparisonExpInfo;
    }

    private  BaseExpressionInfo getBaseExpressionInfo(Expression expression) {
        return new ExpressionHandler().handleExpression(expression, ColumnDetails.builder().build());
    }
}
