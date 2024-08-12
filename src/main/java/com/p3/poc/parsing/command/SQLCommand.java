package com.p3.poc.parsing.command;

import com.p3.poc.parsing.node.SQLNode;

public interface SQLCommand {
    void execute(SQLNode sqlNode);
}
