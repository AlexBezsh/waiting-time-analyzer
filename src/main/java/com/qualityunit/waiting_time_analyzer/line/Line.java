package com.qualityunit.waiting_time_analyzer.line;

import com.qualityunit.waiting_time_analyzer.customer_support_structure.Question;
import com.qualityunit.waiting_time_analyzer.customer_support_structure.Service;

public abstract class Line {
    private final LineType lineType;
    private final Service service;
    private final Question question;
    private final ResponseType responseType;

    public Line(LineType lineType, Service service, Question question, ResponseType responseType) {
        this.lineType = lineType;
        this.service = service;
        this.question = question;
        this.responseType = responseType;
    }

    public LineType getLineType() {
        return lineType;
    }

    public Service getService() {
        return service;
    }

    public Question getQuestion() {
        return question;
    }

    public ResponseType getResponseType() {
        return responseType;
    }
}
