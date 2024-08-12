package com.p3.poc.parsing.node.query_node;

import com.p3.poc.parsing.node.SQLNode;
import com.p3.poc.parsing.node.SQLNodeVisitor;
import lombok.Getter;

@Getter
public class CTENode extends SQLNode {
    private final String cteName;
    private final SQLNode cteQuery; // This would be another SQLNode representing the CTE query.

    public CTENode(String cteName, SQLNode cteQuery) {
        super("CTE");
        this.cteName = cteName;
        this.cteQuery = cteQuery;
    }

    @Override
    public <R> void accept(SQLNodeVisitor<R> visitor) {
        visitor.visit(this);
    }
}
