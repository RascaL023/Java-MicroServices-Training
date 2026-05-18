package com.rascal.auth_service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter @Setter
@ConfigurationProperties(prefix = "auth")
public class AuthModeConfig {

    private String mode = "stateful";

    public boolean isStateful() {
        return "stateful".equalsIgnoreCase(mode);
    }
    
}
