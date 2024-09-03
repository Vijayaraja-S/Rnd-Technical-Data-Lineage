package com.p3.poc.lineage.bean.flow.relationship;

import lombok.*;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Relationship {
    private String id;
    private String type;
    private String function;
    private String effectType;
    private Target target;
    private List<Source> sources;
}
