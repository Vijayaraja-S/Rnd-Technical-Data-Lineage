package com.p3.poc.parser.bean.query.query_body.query_specification.relation.sub_relation;

import com.p3.poc.parser.bean.query.query_body.query_specification.relation.BaseRelationInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.relation.identifier.RelationType;
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
