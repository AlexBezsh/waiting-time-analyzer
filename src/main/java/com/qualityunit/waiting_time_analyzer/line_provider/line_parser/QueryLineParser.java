package com.qualityunit.waiting_time_analyzer.line_provider.line_parser;

import com.qualityunit.waiting_time_analyzer.customer_support_structure.Question;
import com.qualityunit.waiting_time_analyzer.customer_support_structure.Service;
import com.qualityunit.waiting_time_analyzer.exception.IncorrectDataException;
import com.qualityunit.waiting_time_analyzer.line.Query;
import com.qualityunit.waiting_time_analyzer.line.ResponseType;

import java.time.LocalDate;

public class QueryLineParser extends AbstractLineParser {

    public Query parseLine(String line) {
        String[] lineParts = getLineParts(line);
        Service service = parseService(lineParts[1]);
        Question question = parseQuestion(lineParts[2]);
        ResponseType responseType = parseResponseType(lineParts[3]);
        LocalDate[] dateRange = parseDateRange(lineParts[4]);
        return new Query(service, question, responseType, dateRange[0], dateRange[1]);
    }

    private static String[] getLineParts(String line) {
        String[] lineParts = line.split(" ");
        if (lineParts.length != 5) {
            throw new IncorrectDataException("Wrong query line: \"" + line + "\"");
        }
        return lineParts;
    }

    private LocalDate[] parseDateRange(String dateRange) {
        String[] dates = dateRange.split("-");
        LocalDate from;
        LocalDate to;
        if (dates.length == 1) {
            from = parseDate(dates[0]);
            to = LocalDate.MAX;
        } else if (dates.length == 2) {
            from = parseDate(dates[0]);
            to = parseDate(dates[1]);
        } else {
            throw new IncorrectDataException("String: \"" + dateRange + "\" does not contain valid date or range of dates.");
        }

        if (to.isBefore(from)) {
            throw new IncorrectDataException("DateTo precedes dateFrom: \"" + dateRange + "\"");
        }
        return new LocalDate[]{from, to};
    }
}
