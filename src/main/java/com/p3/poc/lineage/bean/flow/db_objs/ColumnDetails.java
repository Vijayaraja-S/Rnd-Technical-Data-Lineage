package com.p3.poc.lineage.bean.flow.db_objs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ColumnDetails {
    @Builder.Default private String columnId="";
    @Builder.Default private String columnName="";
    @Builder.Default private String columnAliasName="";
    @Builder.Default private String columnSource = "Table";
    @Builder.Default private boolean isColumnHaveFunction = false;
    private FunctionDetails columnFunctionDetails;

    //column join details
    @Builder.Default private boolean isJoin=false;
    @Builder.Default private String joinId="";
    @Builder.Default private String joinDetailsId="";
}
