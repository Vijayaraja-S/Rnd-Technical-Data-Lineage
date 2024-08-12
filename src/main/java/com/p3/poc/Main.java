package com.p3.poc;


import com.p3.poc.bean.BeanBuilder;
import com.p3.poc.bean.InputBean;
import com.p3.poc.bean.query_processor.QueryProcessor;


import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        final InputBean inputBean = BeanBuilder.buildInputBean(args[0]);
        final QueryProcessor queryProcessor = new QueryProcessor(inputBean.getSqlQuery());
        queryProcessor.init();
    }
}
