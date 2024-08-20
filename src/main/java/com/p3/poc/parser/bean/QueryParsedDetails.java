package com.p3.poc.parser.bean;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class QueryParsedDetails {
    private WithObjectInfo withQuery;
    @Builder.Default private List<SelectQueryInfo> selectColumns= new ArrayList<>();
}
