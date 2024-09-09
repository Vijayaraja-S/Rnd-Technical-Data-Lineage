package com.p3.poc.parser.bean;

import com.p3.poc.lineage.bean.flow.db_objs.ColumnDetails;
import com.p3.poc.lineage.bean.flow.db_objs.JoinDetailsInfo;
import com.p3.poc.lineage.bean.flow.db_objs.TableDetails;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class GlobalCollector {

    private final Map<String, List<ColumnDetails>> columnListMap = new LinkedHashMap<>();
    private final Map<String, TableDetails> tableMap = new LinkedHashMap<>();
    private final Map<String, JoinDetailsInfo> joinDetailsMap = new LinkedHashMap<>();


    private GlobalCollector() {
    }

    private static class SingletonHelper {
        private static final GlobalCollector INSTANCE = new GlobalCollector();
    }

    public static GlobalCollector getInstance() {
        return SingletonHelper.INSTANCE;
    }


}
