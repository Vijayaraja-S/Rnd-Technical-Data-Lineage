package com.p3.poc.parser.parsing.handler.query;

import com.p3.poc.parser.parsing.handler.CommonQueryParser;
import io.trino.sql.tree.With;
import io.trino.sql.tree.WithQuery;
import lombok.Data;

import java.util.List;


@Data
public class WithHandler implements CommonQueryParser {


    public WithHandler() {
        /* TODO document why this constructor is empty */
    }


    @Override
    public void processQuery(Object node) {
        final With with = (With) node;
        final List<WithQuery> queries = with.getQueries();
    }
}
