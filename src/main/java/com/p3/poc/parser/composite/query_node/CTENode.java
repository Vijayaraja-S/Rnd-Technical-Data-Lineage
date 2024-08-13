package com.p3.poc.parser.composite.query_node;

import com.p3.poc.parser.composite.SQLNode;
import com.p3.poc.parser.visitors.SQLNodeVisitor;
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
