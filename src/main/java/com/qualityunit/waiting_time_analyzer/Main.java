package com.qualityunit.waiting_time_analyzer;

import com.qualityunit.waiting_time_analyzer.data_processor.DataProcessor;
import com.qualityunit.waiting_time_analyzer.data_processor.SequentialDataProcessor;
import com.qualityunit.waiting_time_analyzer.exception.IncorrectDataException;
import com.qualityunit.waiting_time_analyzer.line_provider.LineProvider;
import com.qualityunit.waiting_time_analyzer.line_provider.DirectLineProvider;
import com.qualityunit.waiting_time_analyzer.query_executor.AverageWaitingTimeQueryExecutor;
import com.qualityunit.waiting_time_analyzer.query_executor.QueryExecutor;
import com.qualityunit.waiting_time_analyzer.util.ConsoleHelper;
import com.qualityunit.waiting_time_analyzer.util.DataSourceHelper;

import java.io.BufferedReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = args.length == 1 ? DataSourceHelper.getReader(args[0]) : DataSourceHelper.getReader();
        LineProvider provider = null;
        try {
            provider = new DirectLineProvider(reader);
        } catch (IOException e) {
            ConsoleHelper.exitProgram("An IOException occurred during initialization of line provider");
        }
        QueryExecutor queryExecutor = new AverageWaitingTimeQueryExecutor(System.out);
        DataProcessor dataProcessor = new SequentialDataProcessor(provider, queryExecutor);
        try {
            dataProcessor.processData();
        } catch (IncorrectDataException e) {
            ConsoleHelper.exitProgram("Incorrect data: " + e.getMessage());
        }
    }
}