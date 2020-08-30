package com.qualityunit.waiting_time_analyzer.query_executor;

import com.qualityunit.waiting_time_analyzer.line.Query;
import com.qualityunit.waiting_time_analyzer.line.WaitingTimeLine;

import java.util.List;

public interface QueryExecutor {
    void executeQuery(Query query, List<WaitingTimeLine> data);
}
