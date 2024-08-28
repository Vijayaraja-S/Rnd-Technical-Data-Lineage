package com.p3.poc.parser.bean.query.query_body.query_specification.select;

import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecDetails;
import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecType;
import lombok.*;

import java.util.List;
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectQueryInfo extends QuerySpecDetails {
    @Builder.Default private boolean distinct=false;
    private List<SelectColumnInfo> selectColumnInfo;

    public static SelectQueryInfo getBean(){
        final SelectQueryInfo selectQueryInfo = new SelectQueryInfo();
        selectQueryInfo.setQueryType(QuerySpecType.SELECT);
        return selectQueryInfo;
    }

}
