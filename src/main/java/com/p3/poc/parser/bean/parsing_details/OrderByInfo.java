package com.p3.poc.parser.bean.parsing_details;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderByInfo {
    @Builder.Default
    private String id = "";
    @Builder.Default
    private String columnName = "";
    @Builder.Default
    private String orderType = "";
}
