package com.p3.poc.parser.parsing.handler.expression.service_impl.order_by;

import com.p3.poc.parser.bean.parsing_details.OrderByInfo;
import com.p3.poc.parser.bean.GlobalCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderByHelper {
    void addOrderByDetails(OrderByInfo orderByInfo) {
        final GlobalCollector instance = GlobalCollector.getInstance();
        final String selectId = instance.getDynamicSelectId();
        final Map<String, List<OrderByInfo>> orderByInfoMap = GlobalCollector.getInstance().getOrderByInfoMap();
        if (orderByInfoMap.containsKey(selectId)) {
            orderByInfoMap.get(selectId).add(orderByInfo);
        } else {
            List<OrderByInfo> orderByInfos = new ArrayList<>();
            orderByInfos.add(orderByInfo);
            orderByInfoMap.put(selectId, orderByInfos);
        }
    }
}
