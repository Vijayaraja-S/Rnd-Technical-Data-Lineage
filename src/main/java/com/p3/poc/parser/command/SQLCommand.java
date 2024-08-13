package com.p3.poc.parser.command;

import com.p3.poc.parser.composite.SQLNode;

public interface SQLCommand {
    void execute(SQLNode sqlNode);
}
