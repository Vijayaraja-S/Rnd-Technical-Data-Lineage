package com.p3.poc.lineage;

import lombok.*;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Label{
    private String content;
    private String fontFamily;
    private String fontSize;
    private double height;
    private double width;
    private double x;
    private double y;
}
