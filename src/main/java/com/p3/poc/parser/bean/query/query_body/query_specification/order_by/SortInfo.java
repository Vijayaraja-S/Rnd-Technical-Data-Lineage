package com.p3.poc.parser.bean.query.query_body.query_specification.order_by;


import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import io.trino.sql.tree.SortItem.NullOrdering;
import io.trino.sql.tree.SortItem.Ordering;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class SortInfo {
    private BaseExpressionInfo expressionInfo;
    private Ordering normalOrder;
    private NullOrdering nullOrder;
}
