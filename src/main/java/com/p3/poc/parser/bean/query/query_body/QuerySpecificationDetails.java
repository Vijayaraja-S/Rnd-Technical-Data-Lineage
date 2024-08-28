package com.p3.poc.parser.bean.query.query_body;

import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecDetails;
import com.p3.poc.parser.bean.query.query_body.query_specification.QuerySpecType;
import lombok.*;

import java.util.EnumMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuerySpecificationDetails extends BaseQueryBodyInfo {
    private Map<QuerySpecType, QuerySpecDetails> specificationBeanHashMap = new EnumMap<>(QuerySpecType.class);
}
