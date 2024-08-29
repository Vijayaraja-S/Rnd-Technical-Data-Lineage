package com.p3.poc.parser.bean.query.with;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithInfo {
    private boolean isrecursive;
    @Builder.Default
    private List<WithQueryInfo> withQueryInfos = new ArrayList<>();
}
