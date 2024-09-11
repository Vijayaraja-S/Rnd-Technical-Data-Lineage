package com.p3.poc.parser.bean;

import com.p3.poc.lineage.bean.flow.db_objs.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GlobalCollector {

    private final Map<String, List<ColumnDetails>> overallColumnMap = new LinkedHashMap<>();
    private final Map<String, TableDetails> tableMap = new LinkedHashMap<>();
    private final Map<String, JoinDetailsInfo> joinDetailsMap = new LinkedHashMap<>();
    private final Map<String, List<GroupInfo>> groupInfoMap = new LinkedHashMap<>();
    private final Map<String, List<WhereExpressionInfo>> whereInfoMap = new LinkedHashMap<>();
    private final Map<String, List<HavingExpressionInfo>> havingInfoMap = new LinkedHashMap<>();
    private final Map<String, LimitInfo> limitInfoMap = new LinkedHashMap<>();
    private final Map<String, OffsetInfo> offsetInfoMap = new LinkedHashMap<>();
    private final Map<String, List<OrderByInfo>> orderByInfoMap = new LinkedHashMap<>();

    private String dynamicSelectId;
    private String dynamicGroupId;

    private GlobalCollector() {
    }

    private static class SingletonHelper {
        private static final GlobalCollector INSTANCE = new GlobalCollector();
    }

    public static GlobalCollector getInstance() {
        return SingletonHelper.INSTANCE;
    }

}
