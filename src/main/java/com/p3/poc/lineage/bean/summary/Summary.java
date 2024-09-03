package com.p3.poc.lineage.bean.summary;

import lombok.*;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Summary {
    private int schema;
    private int process;
    private int database;
    private int view;
    private List<MostRelationTable> mostRelationTables;
    private int column;
    private int relationship;
    private int table;
}
