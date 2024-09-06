package com.p3.poc.lineage.bean.graph;

import com.p3.poc.lineage.bean.flow.db_objs.TableDetails;
import lombok.*;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Elements{
    private List<TableDetails> tables;
    private List<Edge> edges;
}
