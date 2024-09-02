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
public class Database{
    private String name;
    private List<Schema> schemas;
}
