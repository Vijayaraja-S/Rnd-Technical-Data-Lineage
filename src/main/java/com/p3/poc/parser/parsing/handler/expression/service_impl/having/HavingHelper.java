package com.p3.poc.parser.parsing.handler.expression.service_impl.having;

import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.handler.expression.bean.HavingExpressionInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HavingHelper {
    public void addHavingDetails(HavingExpressionInfo havingExpressionInfo) {
        final GlobalCollector instance = GlobalCollector.getInstance();
        final String groupId = instance.getDynamicGroupId();
        final Map<String, List<HavingExpressionInfo>> havingInfoMap = GlobalCollector.getInstance().getHavingInfoMap();
        if (havingInfoMap.containsKey(groupId)) {
            havingInfoMap.get(groupId).add(havingExpressionInfo);
        } else {
            List<HavingExpressionInfo> havingExpressionInfos = new ArrayList<>();
            havingExpressionInfos.add(havingExpressionInfo);
            havingInfoMap.put(groupId, havingExpressionInfos);
        }
    }
}
