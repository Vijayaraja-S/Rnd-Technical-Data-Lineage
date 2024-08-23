package com.p3.poc.parser.parsing.handler.query_spec;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.from_relation.BaseRelationInfo;
import com.p3.poc.parser.parsing.handler.relation.CommonRelationHandler;
import io.trino.sql.tree.Relation;
import lombok.Data;

@Data
public class FromHandler {
    private QueryParsedDetails queryParsedDetails;
    private CommonRelationHandler commonRelationHandler;
    public FromHandler(QueryParsedDetails queryParsedDetails) {
        this.queryParsedDetails= queryParsedDetails;
        this.commonRelationHandler = new CommonRelationHandler();
    }

    public void processNode(Relation relation) {
        final BaseRelationInfo baseRelationInfoBean = commonRelationHandler.handleExpression(relation);
        queryParsedDetails.setFromRelationInfo(baseRelationInfoBean);
    }
}
