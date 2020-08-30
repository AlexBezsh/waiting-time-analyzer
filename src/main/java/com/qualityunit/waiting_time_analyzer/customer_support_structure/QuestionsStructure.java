package com.qualityunit.waiting_time_analyzer.customer_support_structure;

import java.util.HashMap;
import java.util.Map;

class QuestionsStructure {

    private final Map<String, Question> questionsStructure;

    private static volatile QuestionsStructure instance;

    private QuestionsStructure() {
        questionsStructure = new HashMap<>();
        init();
    }

    protected static QuestionsStructure getInstance() {
        QuestionsStructure result = instance;
        if (result != null) {
            return result;
        }
        synchronized (QuestionsStructure.class) {
            if (instance == null) {
                instance = new QuestionsStructure();
            }
        }
        return instance;
    }

    private void init() {
        for (int i = 1; i <= 10; i++) {
            String questionTypeId = String.valueOf(i);
            questionsStructure.put(questionTypeId, new Question(questionTypeId));
            for (int j = 1; j <= 20; j++) {
                String categoryId = questionTypeId + "." + j;
                questionsStructure.put(categoryId, new Question(categoryId));
                for (int k = 1; k <= 5; k++) {
                    String subCategoryId = categoryId + "." + k;
                    questionsStructure.put(subCategoryId, new Question(subCategoryId));
                }
            }
        }
    }

    protected Question getQuestion(String fullId) {
        return questionsStructure.get(fullId);
    }
}
