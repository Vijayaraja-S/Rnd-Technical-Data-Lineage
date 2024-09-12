package com.p3.poc.parser.bean;

import com.p3.poc.parser.bean.parsing_details.*;
import com.p3.poc.parser.parsing.handler.expression.bean.HavingExpressionInfo;
import com.p3.poc.parser.parsing.handler.expression.bean.WhereExpressionInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GlobalCollector {

    private final Map<String, List<ColumnDetails>> columnMap = new LinkedHashMap<>();
    private final Map<String, TableDetails> tableMap = new LinkedHashMap<>();
    private final Map<String, JoinDetailsInfo> joinDetailsMap = new LinkedHashMap<>();
    private final Map<String, List<GroupInfo>> groupInfoMap = new LinkedHashMap<>();
    private final Map<String, List<WhereExpressionInfo>> whereInfoMap = new LinkedHashMap<>();
    private final Map<String, List<HavingExpressionInfo>> havingInfoMap = new LinkedHashMap<>();
    private final Map<String, LimitInfo> limitInfoMap = new LinkedHashMap<>();
    private final Map<String, OffsetInfo> offsetInfoMap = new LinkedHashMap<>();
    private final Map<String, List<OrderByInfo>> orderByInfoMap = new LinkedHashMap<>();

    // need to implement(select Result map)


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
