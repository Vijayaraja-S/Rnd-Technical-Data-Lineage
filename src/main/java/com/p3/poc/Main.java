package com.p3.poc;

import com.p3.poc.bean.BeanBuilder;
import com.p3.poc.bean.InputBean;
import com.p3.poc.query_processor.QueryProcessor;

import java.io.IOException;
import java.util.Set;


public class Main {
    public static void main(String[] args) throws IOException {
        final InputBean inputBean = BeanBuilder.buildInputBean(args[0]);
        final QueryProcessor queryProcessor = new QueryProcessor();
        final Set<String> tableListing = queryProcessor.getTableListing(inputBean.getSqlQuery());
        System.out.println(tableListing);
    }

}
