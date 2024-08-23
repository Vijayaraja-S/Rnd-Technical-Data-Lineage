package com.p3.poc.parser.bean.from_relation.sub_relation;

import com.p3.poc.parser.bean.from_relation.BaseRelationInfo;
import com.p3.poc.parser.bean.from_relation.identifier.RelationType;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableRelationInfo extends BaseRelationInfo {
    private String fullTableName;
    private String tableName;
    private String schemaName;

    public static TableRelationInfo getBean() {
        final TableRelationInfo tableRelationInfo = new TableRelationInfo();
        tableRelationInfo.setRelationType(RelationType.TABLE);
        return tableRelationInfo;
    }
}
