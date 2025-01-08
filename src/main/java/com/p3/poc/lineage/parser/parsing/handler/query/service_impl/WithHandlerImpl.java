package com.p3.poc.lineage.parser.parsing.handler.query.service_impl;


import com.p3.poc.lineage.parser.bean.GlobalCollector;
import com.p3.poc.lineage.parser.bean.parsing_details.WithInfo;
import com.p3.poc.lineage.parser.parsing.handler.query.service.WithHandler;
import com.p3.poc.lineage.parser.parsing.handler.statement.StatementHandler;
import io.trino.sql.tree.*;

import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;


public class WithHandlerImpl implements WithHandler {


    @Override
    public void handleWith(With with) {
        final GlobalCollector instance = GlobalCollector.getInstance();
        final Map<String, LinkedList<WithInfo>> childWithInfoMap = instance.getChildWithInfoMap();
        final LinkedList<WithQuery> children = new LinkedList<>(with.getQueries());

        children.forEach(child -> {
            final WithInfo parentOrChildWithInfo = WithInfo.builder()
                    .id(UUID.randomUUID().toString())
                    .withName(String.valueOf(child.getName()))
                    .build();
            if (instance.isParentWith()) {
                instance.getParentWithInfos().add(parentOrChildWithInfo);
                instance.setDynamicParentWithName(parentOrChildWithInfo.getWithName());
                childWithInfoMap.put(parentOrChildWithInfo.getWithName(), new LinkedList<>());
                instance.setParentWith(false);
            } else {
                final String key = instance.getDynamicParentWithName();
                if (childWithInfoMap.containsKey(key)) {
                    final LinkedList<WithInfo> withInfos = childWithInfoMap.get(key);
                    withInfos.add(parentOrChildWithInfo);
                }
            }
            final StatementHandler statementHandler = new StatementHandler();
            final Query query = child.getQuery();
            statementHandler.handleQuery(query);
            instance.setParentWith(true);
        });
    }
}



