package com.p3.poc.lineage;

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
public class ListIdMap {
    private Map<String, List<String>> mappingList =new LinkedHashMap<>();
}
