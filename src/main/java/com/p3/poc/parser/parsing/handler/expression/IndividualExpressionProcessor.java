package com.p3.poc.parser.parsing.handler.expression;

import com.p3.poc.parser.bean.ExpressionDetailInfo;
import com.p3.poc.parser.bean.QueryExpressionInfo;
import io.trino.sql.tree.DereferenceExpression;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.FunctionCall;
import io.trino.sql.tree.Identifier;

import java.util.Optional;

import static com.p3.poc.parser.parsing.handler.expression.indentifier.ExpressionTypes.DE_REFERENCE;

public class IndividualExpressionProcessor {
    
    public QueryExpressionInfo processExpression(QueryExpressionInfo expressionInfo, DereferenceExpression expression) {
        ExpressionDetailInfo expressionDetailInfo = getExpressionDetailInfo();

        final Optional<Identifier> field = expression.getField();
        final Expression base = expression.getBase();

        if (base instanceof Identifier identifier){
         expressionDetailInfo.setBaseReference(identifier.getValue());
        }
        expressionDetailInfo.setColumnName(field.isPresent()?field.get().toString():"");

        expressionInfo.getExpressionType().add(DE_REFERENCE);
        expressionInfo.getExpressionDetails().put(DE_REFERENCE, expressionDetailInfo);
        return expressionInfo;
    }


    public QueryExpressionInfo processExpression(QueryExpressionInfo expressionInfo, FunctionCall expression) {
        return expressionInfo;
    }

    private ExpressionDetailInfo getExpressionDetailInfo() {
        return ExpressionDetailInfo.builder().build();
    }
}
