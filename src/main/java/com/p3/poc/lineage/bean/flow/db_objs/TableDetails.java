package com.p3.poc.lineage.bean.flow.db_objs;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TableDetails {
    private String id;
    private String name;
    private String fullName;
    private String schemaName;
    private String aliasName;
    private List<ColumnDetails> columns;
    @Builder.Default private boolean isHavingJoin=false;
    private JoinDetailsInfo joinDetailsInfo;
//
//    private String type;
//    private List<Coordinate> coordinates;
//    private Label label;
}
