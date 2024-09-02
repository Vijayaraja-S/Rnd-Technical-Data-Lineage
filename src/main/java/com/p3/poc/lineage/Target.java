package com.p3.poc.lineage;

import lombok.*;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Target {
    private String id;
    private String column;
    private String parentId;
    private String parentName;
    private List<Coordinate> coordinates;
}
