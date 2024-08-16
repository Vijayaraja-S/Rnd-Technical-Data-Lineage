package com.p3.poc.parser.bean;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class WithObjectInfo {
    @Builder.Default private boolean isRecursive=false;
    private int numberOfWithQuery;
    private List<WithQueryObjectDetails> withQueryDetails;
    @Builder.Default private Map<UUID,List<WithObjectInfo>> withObjectInfos = new LinkedHashMap<>();
}
