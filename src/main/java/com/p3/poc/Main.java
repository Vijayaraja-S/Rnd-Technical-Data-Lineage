package com.p3.poc;

import com.p3.poc.lineage.QueryProcessor;
import com.p3.poc.lineage.bean.InputBean;
import com.p3.poc.lineage.parser.parsing.exception.InvalidStatement;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            QueryProcessor queryProcessor = new QueryProcessor();
            final InputBean inputBean = queryProcessor.initializeCollectors(args[0]);
            queryProcessor.processInputFile(inputBean);
        } catch (IOException | InvalidStatement e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
