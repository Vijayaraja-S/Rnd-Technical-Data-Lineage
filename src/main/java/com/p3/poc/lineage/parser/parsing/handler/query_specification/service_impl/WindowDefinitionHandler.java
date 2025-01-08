package com.p3.poc.lineage.parser.parsing.handler.query_specification.service_impl;

import com.p3.poc.lineage.parser.parsing.handler.query_specification.service.AbstractQuerySpecHandler;
import io.trino.sql.tree.WindowDefinition;

public class WindowDefinitionHandler extends AbstractQuerySpecHandler {
    private WindowDefinition windowDefinition;

    public WindowDefinitionHandler(WindowDefinition windowsDefinition) {
        this.windowDefinition = windowsDefinition;
    }

    @Override
    public void process() {
        System.out.println(windowDefinition);
    }
}
