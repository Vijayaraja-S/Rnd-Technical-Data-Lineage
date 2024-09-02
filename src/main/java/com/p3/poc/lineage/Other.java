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
public class Other{
    private String id;
    private String name;
    private String displayName;
    private String type;
    private List<Column> columns;
    private List<Coordinate> coordinates;
}
