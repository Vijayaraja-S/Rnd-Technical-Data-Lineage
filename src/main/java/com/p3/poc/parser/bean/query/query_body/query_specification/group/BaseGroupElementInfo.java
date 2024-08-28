package com.p3.poc.parser.bean.query.query_body.query_specification.group;

import com.p3.poc.parser.bean.expression.BaseExpressionInfo;
import com.p3.poc.parser.bean.query.query_body.query_specification.group.identifier.GroupType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
public class BaseGroupElementInfo {
    private GroupType type;
    private List<BaseExpressionInfo> elementExpressionInfoList;

    //list of list expression only for grouping set
    private List<List<BaseExpressionInfo>> groupingSetExpressionInfoList;

    public static BaseGroupElementInfo getBean(){
        return new BaseGroupElementInfo();
    }
}
