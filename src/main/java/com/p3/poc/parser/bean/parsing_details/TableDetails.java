package com.p3.poc.parser.bean.parsing_details;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TableDetails {
    @Builder.Default
    private String id = "";
    @Builder.Default
    private String name = "";
    @Builder.Default
    private String fullName = "";
    @Builder.Default
    private String aliasName = "";
    @Builder.Default
    private String schemaName = "";

}
