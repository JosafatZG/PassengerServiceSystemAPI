package com.passengerservicesystemapi.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ToolsDTO {
    @Value("${spring.application.name}")
    private String applicationName;

    public String getApplicationName() {
        return applicationName;
    }
}
