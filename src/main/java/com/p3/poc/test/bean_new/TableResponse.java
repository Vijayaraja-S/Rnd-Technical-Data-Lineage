package com.p3.poc.test.bean_new;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TableResponse {
    private String tableId;
    private String tableName;
    private List<ColumnResponse> columns;
}
