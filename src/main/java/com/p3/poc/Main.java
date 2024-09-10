package com.p3.poc;


import com.p3.poc.bean.BeanBuilder;
import com.p3.poc.bean.InputBean;
import com.p3.poc.lineage.bean.flow.db_objs.*;
import com.p3.poc.parser.SQLParserApplication;
import com.p3.poc.parser.bean.GlobalCollector;
import com.p3.poc.parser.parsing.exception.InvalidStatement;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IOException, InvalidStatement {
        final InputBean inputBean = BeanBuilder.buildInputBean(args[0]);
        final SQLParserApplication app = new SQLParserApplication();
        app.parse(inputBean.getSqlQuery());
        final GlobalCollector instance = GlobalCollector.getInstance();
        final Map<String, List<ColumnDetails>> columnListMap = instance.getOverallColumnMap();
        final Map<String, TableDetails> tableMap = instance.getTableMap();
        final Map<String, JoinDetailsInfo> joinDetailsMap = instance.getJoinDetailsMap();
        final Map<String, List<GroupInfo>> groupInfoMap = instance.getGroupInfoMap();
        final Map<String, List<WhereExpressionInfo>> whereInfoMap = instance.getWhereInfoMap();
        System.out.println("sdfdsf");
    }
}
