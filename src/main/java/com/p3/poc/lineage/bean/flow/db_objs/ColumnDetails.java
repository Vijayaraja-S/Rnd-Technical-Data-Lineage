package com.p3.poc.lineage.bean.flow.db_objs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ColumnDetails {
    private String columnId;
    private String columnName;
    private String columnAliasName;
    private String columnSource;
    @Builder.Default
    private boolean isColumnHaveFunction = false;
    private FunctionDetails columnFunctionDetails;


//    private List<Coordinate> coordinates;
//    private Label label;
}
