package com.p3.poc.parser.parsing.handler.expression;

import com.p3.poc.parser.bean.expression.DeReferenceExpressionDetailInfo;
import com.p3.poc.parser.bean.expression.ExpressionDetails;
import io.trino.sql.tree.DereferenceExpression;
import io.trino.sql.tree.Expression;
import io.trino.sql.tree.FunctionCall;
import io.trino.sql.tree.Identifier;

import java.util.Optional;

import static com.p3.poc.parser.parsing.handler.expression.indentifier.ExpressionTypes.DE_REFERENCE;

public class IndividualExpressionProcessor {
    
    public ExpressionDetails processExpression(ExpressionDetails expressionInfo, DereferenceExpression expression) {
        DeReferenceExpressionDetailInfo expressionDetailInfo = getExpressionDetailInfo();

        final Optional<Identifier> field = expression.getField();
        final Expression base = expression.getBase();

        if (base instanceof Identifier identifier){
         expressionDetailInfo.setBaseReference(identifier.getValue());
        }
        expressionDetailInfo.setColumnName(field.isPresent()?field.get().toString():"");

        expressionInfo.getExpressionType().add(DE_REFERENCE);
        expressionInfo.setDeReferenceExpressionDetailInfo(expressionDetailInfo);
        return expressionInfo;
    }


    public ExpressionDetails processExpression(ExpressionDetails expressionInfo, FunctionCall expression) {
        return expressionInfo;
    }

    private DeReferenceExpressionDetailInfo getExpressionDetailInfo() {
        return DeReferenceExpressionDetailInfo.builder().build();
    }
}
