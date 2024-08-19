package com.p3.poc.parser.parsing.handler.query.operations;

import com.p3.poc.parser.bean.QueryParsedDetails;
import com.p3.poc.parser.bean.WithObjectInfo;
import com.p3.poc.parser.bean.WithQueryObjectDetails;
import com.p3.poc.parser.parsing.handler.query.QueryProcessor;
import io.trino.sql.tree.Node;
import io.trino.sql.tree.With;
import io.trino.sql.tree.WithQuery;
import lombok.Data;

import java.util.List;


@Data
public class WithHandler implements QueryProcessor {
    private final QueryParsedDetails queryDetails;
    private WithQueryObjectDetails withQueryObjectDetails;
    private  WithObjectInfo initialWithObjectBean;

    public WithHandler(QueryParsedDetails queryDetails) {
        this.queryDetails = queryDetails;
        this.withQueryObjectDetails = new WithQueryObjectDetails();
    }


    @Override
    public void processQueryObject(Node node) {
        final With withNode = (With) node;
        this.initialWithObjectBean = WithObjectInfo.builder().build();
        List<WithQueryObjectDetails> resultWithQueryObject = withNode.getQueries()
                .stream()
                .map(this::processWithNode)
                .toList();

        initialWithObjectBean.setWithQueryDetails(resultWithQueryObject);
        queryDetails.setWithQuery(initialWithObjectBean);
    }



    private WithQueryObjectDetails processWithNode(WithQuery withQuery) {
        return null;
    }
}
