package com.p3.poc.parsing.node.query_node;

import com.p3.poc.parsing.node.SQLNode;
import com.p3.poc.parsing.node.SQLNodeVisitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WindowFunctionNode extends SQLNode {
    private String functionName; // e.g., ROW_NUMBER, RANK, NTILE, LEAD, LAG
    private String partitionBy;
    private String orderBy;
    private String frameSpecification;

    public WindowFunctionNode(String functionName, String partitionBy, String orderBy, String frameSpecification) {
        super("WINDOW");
        this.functionName = functionName;
        this.partitionBy = partitionBy;
        this.orderBy = orderBy;
        this.frameSpecification = frameSpecification;
    }

    @Override
    public <R> void accept(SQLNodeVisitor<R> visitor) {
        visitor.visit(this);
    }
}
