package com.p3.poc.lineage.sunburst_chart;

import com.p3.poc.lineage.parser.bean.parsing_details.ApplicationDetails;
import com.p3.poc.lineage.parser.bean.parsing_details.ColumnDetails;
import com.p3.poc.lineage.parser.bean.parsing_details.SchemaDetails;
import com.p3.poc.lineage.parser.bean.parsing_details.TableDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SunBurstGlobalCollector {

    private final Map<String, List<ColumnDetails>> overAllColumMap = new LinkedHashMap<>();
    private final Map<String, List<TableDetails>> overAllTableMap = new LinkedHashMap<>();
    private final Map<String, List<SchemaDetails>> overAllschemaMap = new LinkedHashMap<>();
    private final Map<String, List<ApplicationDetails>> overAllApplicationMap = new LinkedHashMap<>();

    private final Map<String,List<TableDetails>> duplicateTablesdetailsMap = new LinkedHashMap<>();

    private String searchName;
    private String searchId;


    private static class SingletonHelper {
        private static final SunBurstGlobalCollector INSTANCE = new SunBurstGlobalCollector();
    }

    public static SunBurstGlobalCollector getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void resetCollector() {
        overAllColumMap.clear();
        overAllTableMap.clear();
        overAllschemaMap.clear();
        overAllApplicationMap.clear();
    }

}
