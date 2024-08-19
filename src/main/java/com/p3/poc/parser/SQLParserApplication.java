package com.p3.poc.parser;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.parsing.exception.InvalidStatement;
import com.p3.poc.parser.parsing.utils.HandlerChecker;
import io.trino.sql.parser.ParsingOptions;
import io.trino.sql.parser.SqlParser;
import io.trino.sql.tree.Query;
import io.trino.sql.tree.Statement;
import lombok.extern.slf4j.Slf4j;

import java.util.AbstractMap;
import java.util.Optional;

@Slf4j
public class SQLParserApplication {

    public QueryParsedDetails parse(String sqlQuery) throws InvalidStatement {
        SqlParser parser = new SqlParser();
        Statement statement = parser.createStatement(sqlQuery, new ParsingOptions());
        if (statement instanceof Query query) {
            var children = query.getChildren();

            if (children.isEmpty()) {
                log.warn("The query object is empty");
            } else {
                Optional.of(children)
                        .ifPresent(childList -> childList.stream()
                                .map(child -> new AbstractMap.SimpleEntry<>(child, HandlerChecker.getHandler(child)))
                                .filter(entry -> entry.getValue() != null)
                                .forEach(entry -> entry.getValue().processQuery(entry.getKey())));
            }
        } else {
            throw new InvalidStatement("Invalid statement object");
        }
        return null;
    }


}
