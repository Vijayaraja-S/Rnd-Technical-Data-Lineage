package com.p3.poc.parser.parsing.handler;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.parsing.utils.HandlerChecker;
import io.trino.sql.tree.Node;
import lombok.Data;

@Data
public class BaseClassHandler implements CommonQueryParser {
    private QueryParsedDetails queryParsedDetails;

    public BaseClassHandler(QueryParsedDetails queryDetails) {
        this.queryParsedDetails = queryDetails;
    }


    @Override
    public void processQuery(Object node) {
        final Node nodeObject = (Node) node;

        var children = nodeObject.getChildren();
        if (!children.isEmpty()){
            children
                    .forEach(child -> {
                        CommonQueryParser handler = HandlerChecker.getHandler(child);
                        handler.processQuery(child);
                    });
        }
    }
}
