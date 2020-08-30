package com.qualityunit.waiting_time_analyzer.query_executor;

import com.qualityunit.waiting_time_analyzer.line.Query;
import com.qualityunit.waiting_time_analyzer.line.WaitingTimeLine;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AverageWaitingTimeQueryExecutor extends AbstractQueryExecutor {

    public AverageWaitingTimeQueryExecutor(PrintStream resultOutputDestination) {
        super(resultOutputDestination);
    }

    /**
     * Calculates average waiting time for a waiting time lines,
     * specified in the query, using data from list, passed as an argument.
     * Prints average waiting time to the output destination.
     * If there is no data, specified in the query, prints "-".
     * @param query query line
     * @param data list of waiting time lines
     */
    public void executeQuery(Query query, List<WaitingTimeLine> data) {
        List<WaitingTimeLine> resultList = new ArrayList<>();
        for (WaitingTimeLine waitingTimeLine : data) {
            if ((query.getService().getFullId().equals("*")
                        || waitingTimeLine.getService().getFullId().startsWith(query.getService().getFullId()))
                    &&(query.getQuestion().getFullId().equals("*")
                        || waitingTimeLine.getQuestion().getFullId().startsWith(query.getQuestion().getFullId()))
                    && waitingTimeLine.getResponseType() == query.getResponseType()
                    && !waitingTimeLine.getDate().isBefore(query.getDateFrom()) // date from - inclusive
                    && waitingTimeLine.getDate().isBefore(query.getDateTo())) { // date to - exclusive
                resultList.add(waitingTimeLine);
            }
        }

        printResult(resultList);
    }

    private void printResult(List<WaitingTimeLine> resultList) {
        if (resultList.size() == 0) {
            resultOutputDestination.println("-");
        } else {
            int sum = resultList.stream().mapToInt(WaitingTimeLine::getResponseTime).sum();
            resultOutputDestination.println(sum / resultList.size());
        }
    }
}
