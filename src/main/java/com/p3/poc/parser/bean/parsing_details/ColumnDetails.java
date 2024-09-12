package com.p3.poc.parser.bean.parsing_details;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ColumnDetails {
    @Builder.Default private String id ="";
    @Builder.Default private String columnName="";
    @Builder.Default private String columnAliasName="";
    @Builder.Default private String columnSource = "DEFAULT_TABLE";

    //
    @Builder.Default private boolean isColumnHaveFunction = false;
    @Builder.Default private String functionName="";
    @Builder.Default private String functionContent="";
    @Builder.Default private String functionType="";
    //column join details
    @Builder.Default private boolean isJoin=false;
    @Builder.Default private String joinId="";
    @Builder.Default private String joinDetailsId="";
}
