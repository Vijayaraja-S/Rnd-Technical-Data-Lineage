package com.p3.poc.parser.bean;

import com.p3.poc.parser.bean.query.with.WithQueryInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CollectorsUtil {
    private CollectorsUtil() {
        //
    }
    private  static int count = 0;
    private  static int WithQueryInfoCount = 0;

    public static Map<String, List<WithQueryInfo>> withQueryInfoMap = new LinkedHashMap<>();

    public static String getWithCount() {
        if (count==0){
            count++;
            return "root";
        }
        return String.valueOf(count++);
    }

    public static String getWithQueryInfoCount() {
        if (WithQueryInfoCount==0){
            WithQueryInfoCount++;
            return "root";
        }
        return String.valueOf(WithQueryInfoCount++);
    }
}
