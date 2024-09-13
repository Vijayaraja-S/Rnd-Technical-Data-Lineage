package com.p3.poc.parser.bean;

import com.p3.poc.parser.bean.parsing_details.*;
import com.p3.poc.parser.parsing.handler.expression.bean.HavingExpressionInfo;
import com.p3.poc.parser.parsing.handler.expression.bean.WhereExpressionInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class GlobalCollector {

    private final Map<String, List<ColumnDetails>> overAllColumMap = new LinkedHashMap<>();
    private final Map<String, List<TableDetails>> overAllTableMap = new LinkedHashMap<>();
    private final Map<String, List<SchemaDetails>> overAllschemaMap = new LinkedHashMap<>();
    private final Map<String, List<ApplicationDetails>> overAllApplicationMap = new LinkedHashMap<>();

    //
    private final Map<String, JoinDetailsInfo> joinDetailsMap = new LinkedHashMap<>();
    private final Map<String, List<GroupInfo>> groupInfoMap = new LinkedHashMap<>();
    private final Map<String, List<WhereExpressionInfo>> whereInfoMap = new LinkedHashMap<>();
    private final Map<String, List<HavingExpressionInfo>> havingInfoMap = new LinkedHashMap<>();
    private final Map<String, LimitInfo> limitInfoMap = new LinkedHashMap<>();
    private final Map<String, OffsetInfo> offsetInfoMap = new LinkedHashMap<>();
    private final Map<String, List<OrderByInfo>> orderByInfoMap = new LinkedHashMap<>();

    //
    private final List<WithInfo> parentWithInfos = new LinkedList<>();
    private final Map<String, LinkedList<WithInfo>> childWithInfoMap = new LinkedHashMap<>();
    private final Map<String, String> withSelectMapping = new LinkedHashMap<>();

    //
    private final Map<String, LinkedList<ColumnDetails>> individualSelectResultMap = new LinkedHashMap<>();


    private boolean isParentWith;
    private String dynamicParentWithName;

    private String dynamicSelectId;
    private String dynamicGroupId;

    private String searchName;
    private String searchId;

    private GlobalCollector() {
    }

    private static class SingletonHelper {
        private static final GlobalCollector INSTANCE = new GlobalCollector();
    }

    public static GlobalCollector getInstance() {
        return SingletonHelper.INSTANCE;
    }

}
