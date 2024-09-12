package com.p3.poc.parser.bean.parsing_details;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FunctionDetails {
    @Builder.Default private String functionName="";
    @Builder.Default private String functionDescription="";
}
