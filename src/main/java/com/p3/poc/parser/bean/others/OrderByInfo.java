package com.p3.poc.parser.bean.others;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import io.trino.sql.tree.SortItem.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderByInfo {
    private List<SortInfo> sortInfos;

    @Data
    @Getter
    @Setter
    @Builder
    public static class SortInfo {
        private BaseExpressionInfo expressionInfo;
        private Ordering normalOrder;
        private NullOrdering nullOrder;
    }
}
