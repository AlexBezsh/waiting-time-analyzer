package com.qualityunit.waiting_time_analyzer.customer_support_structure;

public class CustomerSupportStructure {

    private final QuestionsStructure questionsStructure;
    private final ServicesStructure servicesStructure;
    private final Question questionStub = new Question("*");
    private final Service serviceStub = new Service("*");

    private static volatile CustomerSupportStructure instance;

    private CustomerSupportStructure() {
        questionsStructure = QuestionsStructure.getInstance();
        servicesStructure = ServicesStructure.getInstance();
    }

    public static CustomerSupportStructure getInstance() {
        CustomerSupportStructure result = instance;
        if (result != null) {
            return result;
        }
        synchronized (CustomerSupportStructure.class) {
            if (instance == null) {
                instance = new CustomerSupportStructure();
            }
        }
        return instance;
    }

    /**
     * Returns an object of the <code>{@link Question}</code> class with id passed as an argument.
     * If argument equals "*" returns question with id "*".
     * <p>Throws an IllegalArgumentException if a question with given id does not exist in the questions structure.
     * @return Question with id passed as an argument
     * @throws IllegalArgumentException if question with given id doesn't exist.
     */
    public Question getQuestionById(String fullQuestionId) {
        if (fullQuestionId.equals("*")) {
            return questionStub;
        }
        Question question = questionsStructure.getQuestion(fullQuestionId);
        if (question != null) {
            return question;
        }
        throw new IllegalArgumentException("There is no question that can be identified by id: " + fullQuestionId);
    }

    /**
     * Returns an object of the <code>{@link Service}</code> class with id passed as an argument.
     * If argument equals "*" returns service with id "*".
     * <p>Throws an IllegalArgumentException if a service with given id does not exist in the services structure.
     * @return Service with id passed as an argument
     * @throws IllegalArgumentException if service with given id doesn't exist.
     */
    public Service getServiceById(String fullServiceId) {
        if (fullServiceId.equals("*")) {
            return serviceStub;
        }
        Service service = servicesStructure.getService(fullServiceId);
        if (service != null) {
            return service;
        }
        throw new IllegalArgumentException("There is no service that can be identified by id: " + fullServiceId);
    }
}
