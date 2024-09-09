package com.p3.poc.lineage.bean.flow.db_objs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class JoinDetailsInfo {
    private String id;
    private String joinType;
    private String joinEquation;
    private ColumnDetails leftColumn;
    private ColumnDetails rightColumn;
    private String operationInfo;
}
