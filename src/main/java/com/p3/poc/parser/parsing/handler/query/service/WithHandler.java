package com.p3.poc.parser.parsing.handler.query.service;


import io.trino.sql.tree.With;

public interface WithHandler {
    void handleWith(With with);
}
