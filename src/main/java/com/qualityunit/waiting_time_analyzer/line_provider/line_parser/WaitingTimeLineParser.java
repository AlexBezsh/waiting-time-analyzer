package com.qualityunit.waiting_time_analyzer.line_provider.line_parser;

import com.qualityunit.waiting_time_analyzer.customer_support_structure.Question;
import com.qualityunit.waiting_time_analyzer.customer_support_structure.Service;
import com.qualityunit.waiting_time_analyzer.exception.IncorrectDataException;
import com.qualityunit.waiting_time_analyzer.line.ResponseType;
import com.qualityunit.waiting_time_analyzer.line.WaitingTimeLine;

import java.time.LocalDate;

public class WaitingTimeLineParser extends AbstractLineParser {

    private static volatile WaitingTimeLineParser instance;

    private WaitingTimeLineParser() {
    }

    protected static WaitingTimeLineParser getInstance() {
        WaitingTimeLineParser result = instance;
        if (result != null) {
            return result;
        }
        synchronized (WaitingTimeLineParser.class) {
            if (instance == null) {
                instance = new WaitingTimeLineParser();
            }
        }
        return instance;
    }

    public WaitingTimeLine parseLine(String line) {
        String[] lineParts = getLineParts(line);
        Service service = getValidService(lineParts[1]);
        Question question = getValidQuestion(lineParts[2]);
        ResponseType responseType = parseResponseType(lineParts[3]);
        LocalDate date = parseDate(lineParts[4]);
        int responseTime = parseResponseTime(lineParts[5]);
        return new WaitingTimeLine(service, question, responseType, date, responseTime);
    }

    private static String[] getLineParts(String line) {
        String[] lineParts = line.split(" ");
        if (lineParts.length != 6) {
            throw new IncorrectDataException("Wrong waiting time line: \"" + line + "\"");
        }
        return lineParts;
    }

    private Service getValidService(String serviceId) {
        if (serviceId.equals("*")) {
            throw new IncorrectDataException("Service id should be defined in waiting time line: \"" + serviceId + "\"");
        } else {
            return parseService(serviceId);
        }
    }

    private Question getValidQuestion(String questionId) {
        if (questionId.equals("*")) {
            throw new IncorrectDataException("Question id should be defined in waiting time line: \"" + questionId + "\"");
        } else {
            return parseQuestion(questionId);
        }
    }

    private int parseResponseTime(String responseTimeString) {
        int responseTime;
        try {
            responseTime = Integer.parseInt(responseTimeString);
            if (responseTime < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new IncorrectDataException("Wrong response time: " + responseTimeString);
        }
        return responseTime;
    }
}
