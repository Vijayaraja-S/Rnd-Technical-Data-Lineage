package com.p3.poc.lineage.bean.flow.db_objs;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TableDetails {
    @Builder.Default
    private String id = "";
    @Builder.Default
    private String name = "";
    @Builder.Default
    private String fullName = "";
    @Builder.Default
    private String schemaName = "";
    @Builder.Default
    private String aliasName = "";
}
