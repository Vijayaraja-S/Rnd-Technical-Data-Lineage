package com.p3.poc.lineage.bean.flow;

import com.p3.poc.lineage.bean.flow.relationship.Relationship;
import lombok.*;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SqlQueryFlow {
    private DataBaseObjects dbObjs;
    private List<Relationship> relationships;
    private List<Object> processes;
}
