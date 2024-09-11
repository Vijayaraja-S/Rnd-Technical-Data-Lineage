package com.p3.poc.parser.parsing.handler.expression.service_impl.join;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.lineage.bean.flow.db_objs.JoinDetailsInfo;
import com.p3.poc.parser.bean.GlobalCollector;

import java.util.Map;

public class JoinHelper {

    public JoinDetailsInfo saveJoinDetailsInfo(io.trino.sql.tree.ComparisonExpression comparisonExpression, String joinId) {
        final Map<String, JoinDetailsInfo> joinDetailsMap = GlobalCollector.getInstance().getJoinDetailsMap();

        final JoinDetailsInfo detailsInfo = JoinDetailsInfo.builder()
                .detailsId("jd:" + joinDetailsMap.size())
                .joinId(joinId)
                .joinCondition(comparisonExpression.toString())
                .joinEquation(comparisonExpression.getOperator().toString())
                .build();
        joinDetailsMap.put(detailsInfo.getDetailsId(), detailsInfo);
        return detailsInfo;
    }

    public void setJoinProperties(ColumnDetails source, String joinId, JoinDetailsInfo joinDetailsInfo) {
        source.setJoin(true);
        source.setJoinId(joinId);
        source.setJoinDetailsId(joinDetailsInfo.getDetailsId());
    }
}
