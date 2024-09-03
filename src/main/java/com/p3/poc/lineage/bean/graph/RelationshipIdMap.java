package com.p3.poc.lineage.bean.graph;

import lombok.*;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RelationshipIdMap{
    @Builder.Default private Map<String, List<String>> relationMappingId =new LinkedHashMap<>();
}
