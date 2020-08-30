package com.qualityunit.waiting_time_analyzer.customer_support_structure;

import java.util.Objects;

public class Question {
    private final String fullId;

    protected Question(String fullId) {
        this.fullId = fullId;
    }

    public String getFullId() {
        return fullId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "fullId='" + fullId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return fullId.equals(question.fullId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullId);
    }
}
