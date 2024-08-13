package com.p3.poc.parser.composite.query_node;

import com.p3.poc.parser.composite.SQLNode;
import com.p3.poc.parser.visitors.SQLNodeVisitor;

import lombok.Getter;


@Getter
public class JoinNode extends SQLNode {
    private final String joinType; // INNER, LEFT, FULL OUTER, CROSS
    private final String leftTable;
    private final String rightTable;
    private final String joinCondition;

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
