package com.qualityunit.waiting_time_analyzer.customer_support_structure;

import java.util.Objects;

public class Service {
    private final String fullId;

    protected Service(String fullId) {
        this.fullId = fullId;
    }

    public String getFullId() {
        return fullId;
    }

    @Override
    public String toString() {
        return "Service{" +
                "fullId='" + fullId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return fullId.equals(service.fullId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullId);
    }
}
