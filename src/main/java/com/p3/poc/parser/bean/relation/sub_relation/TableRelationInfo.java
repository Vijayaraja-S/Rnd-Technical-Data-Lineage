package com.p3.poc.parser.bean.relation.sub_relation;

import com.p3.poc.parser.bean.relation.BaseRelationInfo;
import com.p3.poc.parser.bean.relation.identifier.RelationType;
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
