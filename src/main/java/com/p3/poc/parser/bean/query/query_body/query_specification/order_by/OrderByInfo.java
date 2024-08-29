package com.p3.poc.parser.bean.query.query_body.query_specification.order_by;

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

    public static OrderByInfo getBean() {
        return new OrderByInfo();
    }
}
