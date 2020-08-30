package com.qualityunit.waiting_time_analyzer.data_processor;

import com.qualityunit.waiting_time_analyzer.util.ConsoleHelper;
import com.qualityunit.waiting_time_analyzer.line.Line;
import com.qualityunit.waiting_time_analyzer.line.LineType;
import com.qualityunit.waiting_time_analyzer.line.Query;
import com.qualityunit.waiting_time_analyzer.line.WaitingTimeLine;
import com.qualityunit.waiting_time_analyzer.line_provider.LineProvider;
import com.qualityunit.waiting_time_analyzer.query_executor.QueryExecutor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * When a LineProvider provides a query, this data processor executes it immediately
 * using the waiting time lines obtained before this query.
 */
public class SequentialDataProcessor implements DataProcessor {
    private final LineProvider lineProvider;
    private final QueryExecutor queryExecutor;

    public SequentialDataProcessor(LineProvider lineProvider, QueryExecutor queryExecutor) {
        this.lineProvider = lineProvider;
        this.queryExecutor = queryExecutor;
    }

    /**
     * Takes lines from <code>{@link LineProvider}</code> until it return's null.
     * <p>If line is a waiting time line, add's it in the list.
     * <p>if it is a query line, executes it immediately using the waiting time lines obtained before this query.
     */
    public void processData() {
        List<WaitingTimeLine> waitingTimeLines = new ArrayList<>();
        try {
            Line line;
            while ((line = lineProvider.getNextLine()) != null) {
                if (line.getLineType() == LineType.C) {
                    waitingTimeLines.add((WaitingTimeLine) line);
                } else {
                    queryExecutor.executeQuery((Query) line, waitingTimeLines);
                }
            }
        } catch (IOException e) {
            ConsoleHelper.print("IOException occurred");
        }
    }
}
