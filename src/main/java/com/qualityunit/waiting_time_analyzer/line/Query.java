package com.qualityunit.waiting_time_analyzer.line;

import com.qualityunit.waiting_time_analyzer.customer_support_structure.Question;
import com.qualityunit.waiting_time_analyzer.customer_support_structure.Service;

import java.time.LocalDate;

public class Query extends Line {
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    public Query(Service service, Question question, ResponseType responseType, LocalDate dateFrom, LocalDate dateTo) {
        super(LineType.D, service, question, responseType);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}