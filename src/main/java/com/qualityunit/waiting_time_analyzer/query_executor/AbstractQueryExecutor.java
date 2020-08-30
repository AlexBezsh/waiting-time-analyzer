package com.qualityunit.waiting_time_analyzer.query_executor;

import java.io.PrintStream;

public abstract class AbstractQueryExecutor implements QueryExecutor {
    protected final PrintStream resultOutputDestination;

    public AbstractQueryExecutor(PrintStream resultOutputDestination) {
        this.resultOutputDestination = resultOutputDestination;
    }
}
