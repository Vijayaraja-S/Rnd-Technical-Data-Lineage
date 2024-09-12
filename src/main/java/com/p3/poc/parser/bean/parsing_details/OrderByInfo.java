package com.p3.poc.parser.bean.parsing_details;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderByInfo {
    private String columnId;
    private String columnName;
    private String orderType;
}
