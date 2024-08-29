package com.p3.poc.parser.bean.query.query_body.query_specification.select;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectQueryInfo{
    @Builder.Default private boolean distinct=false;
    private List<SelectColumnInfo> selectColumnInfo;

    public static SelectQueryInfo getBean(){
        return new SelectQueryInfo();
    }

}
