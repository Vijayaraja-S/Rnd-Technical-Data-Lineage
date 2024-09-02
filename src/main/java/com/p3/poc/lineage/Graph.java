package com.p3.poc.lineage;

import lombok.*;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Graph{
    private RelationshipIdMap relationshipIdMap;
    private Elements elements;
    private Tooltip tooltip;
    private ListIdMap listIdMap;
}
