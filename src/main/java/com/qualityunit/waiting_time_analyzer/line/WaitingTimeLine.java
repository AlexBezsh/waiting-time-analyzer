package com.qualityunit.waiting_time_analyzer.line;

import com.qualityunit.waiting_time_analyzer.customer_support_structure.Question;
import com.qualityunit.waiting_time_analyzer.customer_support_structure.Service;

import java.time.LocalDate;

public class WaitingTimeLine extends Line {
    private final LocalDate date;
    private final int responseTime;

    public WaitingTimeLine(Service service, Question question, ResponseType responseType, LocalDate date, int responseTime) {
        super(LineType.C, service, question, responseType);
        this.date = date;
        this.responseTime = responseTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getResponseTime() {
        return responseTime;
    }
}
