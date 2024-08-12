package com.p3.poc.parser.command;

import com.p3.poc.parser.node.SQLNode;

public interface SQLCommand {
    void execute(SQLNode sqlNode);
}
