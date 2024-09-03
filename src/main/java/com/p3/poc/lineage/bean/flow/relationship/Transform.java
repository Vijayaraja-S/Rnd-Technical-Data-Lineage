package com.p3.poc.lineage.bean.flow.relationship;

import com.p3.poc.lineage.bean.flow.Coordinate;
import lombok.*;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Transform {
    private String type;
    private String code;
    private List<Coordinate> coordinates;
}
