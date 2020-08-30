package com.qualityunit.waiting_time_analyzer.line_provider.line_parser;

import com.qualityunit.waiting_time_analyzer.customer_support_structure.CustomerSupportStructure;
import com.qualityunit.waiting_time_analyzer.customer_support_structure.Question;
import com.qualityunit.waiting_time_analyzer.customer_support_structure.Service;
import com.qualityunit.waiting_time_analyzer.exception.IncorrectDataException;
import com.qualityunit.waiting_time_analyzer.line.ResponseType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class AbstractLineParser implements LineParser {
    protected final CustomerSupportStructure customerSupportStructure = CustomerSupportStructure.getInstance();
    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d.M.yyyy");

    protected Service parseService(String serviceId) {
        Service service;
        try {
            service = customerSupportStructure.getServiceById(serviceId);
        } catch (IllegalArgumentException e) {
            throw new IncorrectDataException("Wrong service id: \"" + serviceId + "\"");
        }
        return service;
    }

    protected Question parseQuestion(String questionId) {
        Question question;
        try {
            question = customerSupportStructure.getQuestionById(questionId);
        } catch (IllegalArgumentException e) {
            throw new IncorrectDataException("Wrong question id: \"" + questionId + "\"");
        }
        return question;
    }

    protected ResponseType parseResponseType(String responceTypeString) {
        ResponseType responseType;
        try {
            responseType = ResponseType.valueOf(responceTypeString);
        } catch (IllegalArgumentException e) {
            throw new IncorrectDataException("\"" + responceTypeString + "\" is not a type of response.");
        }
        return responseType;
    }

    protected LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IncorrectDataException("String: \"" + dateString + "\" does not match the date pattern.");
        }
    }
}
