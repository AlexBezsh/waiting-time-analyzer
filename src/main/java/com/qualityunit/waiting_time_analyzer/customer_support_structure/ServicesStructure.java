package com.qualityunit.waiting_time_analyzer.customer_support_structure;

import java.util.HashMap;
import java.util.Map;

class ServicesStructure {

    private final Map<String, Service> servicesStructure;

    private static volatile ServicesStructure instance;

    private ServicesStructure() {
        servicesStructure = new HashMap<>();
        init();
    }

    protected static ServicesStructure getInstance() {
        ServicesStructure result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ServicesStructure.class) {
            if (instance == null) {
                instance = new ServicesStructure();
            }
        }
        return instance;
    }

    private void init() {
        for (int i = 1; i <= 10; i++) {
            String serviceId = String.valueOf(i);
            servicesStructure.put(serviceId, new Service(serviceId));
            for (int j = 1; j <= 3; j++) {
                String variationId = serviceId + "." + j;
                servicesStructure.put(variationId, new Service(variationId));
            }
        }
    }

    protected Service getService(String fullId) {
        return servicesStructure.get(fullId);
    }
}
