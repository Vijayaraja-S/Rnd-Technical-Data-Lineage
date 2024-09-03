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
public class Source{
    private String id;
    private String column;
    private String parentId;
    private String parentName;
    private List<Coordinate> coordinates;
    private String clauseType;
    private List<Transform> transforms;
}
