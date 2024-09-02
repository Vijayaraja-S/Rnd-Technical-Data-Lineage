package com.p3.poc.lineage;

import lombok.*;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Edge {
    private String id;
    private String sourceId;
    private String targetId;
}
