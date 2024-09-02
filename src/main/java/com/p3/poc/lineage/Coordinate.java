package com.p3.poc.lineage;

import lombok.*;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Coordinate{
    private String hashCode;
    private int x;
    private int y;
}
