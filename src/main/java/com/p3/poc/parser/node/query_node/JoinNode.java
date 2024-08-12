package com.p3.poc.parser.node.query_node;

import com.p3.poc.parser.node.SQLNode;
import com.p3.poc.parser.node.SQLNodeVisitor;

public class JoinNode extends SQLNode {
    private String joinType; // INNER, LEFT, FULL OUTER, CROSS
    private String leftTable;
    private String rightTable;
    private String joinCondition;

    public JoinNode(String joinType,
                    String leftTable, String rightTable, String joinCondition) {
        super("JOIN");
        this.joinType = joinType;
        this.leftTable = leftTable;
        this.rightTable = rightTable;
        this.joinCondition = joinCondition;
    }


    @Override
    public <R> void accept(SQLNodeVisitor<R> visitor) {
        visitor.visit(this);
    }
}
