package com.p3.poc.test.bean_new;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ColumnResponse {
    private String ColumId;
    private String columnName;
}
