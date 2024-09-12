package com.p3.poc.parser.parsing.handler.expression.service_impl.join;

import com.p3.poc.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.parser.bean.parsing_details.JoinDetailsInfo;
import com.p3.poc.parser.bean.GlobalCollector;
import io.trino.sql.tree.ComparisonExpression;

import java.util.Map;

public class JoinHelper {

    public void saveJoinDetailsInfo(ComparisonExpression comparisonExpression, String joinId) {
        final Map<String, JoinDetailsInfo> joinDetailsMap = GlobalCollector.getInstance().getJoinDetailsMap();

        final JoinDetailsInfo detailsInfo = JoinDetailsInfo.builder()
                .id("jd:" + joinDetailsMap.size())
                .joinId(joinId)
                .joinCondition(comparisonExpression.toString())
                .joinEquation(comparisonExpression.getOperator().toString())
                .build();
        joinDetailsMap.put(detailsInfo.getId(), detailsInfo);
    }

    public void setJoinProperties(ColumnDetails source, String joinId, JoinDetailsInfo joinDetailsInfo) {
        source.setJoin(true);
        source.setJoinId(joinId);
        source.setJoinDetailsId(joinDetailsInfo.getId());
    }
}
