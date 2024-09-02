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
public class Column{
    private String id;
    private String name;
    private List<Coordinate> coordinates;
    private String source;
    private boolean uiVisible;
    private double height;
    private Label label;
    private double width;
    private double x;
    private double y;
}
