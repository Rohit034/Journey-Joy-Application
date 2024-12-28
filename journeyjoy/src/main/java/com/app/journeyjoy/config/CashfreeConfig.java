package com.app.journeyjoy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import com.cashfree.*;

@Configuration
public class CashfreeConfig {

    @Value("${cashfree.api.key}")
    private String apiKey;

    @Value("${cashfree.secret.key}")
    private String secretKey;

    @Value("${cashfree.environment}")
    private String environment;

    @PostConstruct
    public void initialize() {
        Cashfree.XClientId = apiKey;
        Cashfree.XClientSecret = secretKey;
        Cashfree.XEnvironment = environment.equalsIgnoreCase("sandbox")
                ? Cashfree.SANDBOX
                : Cashfree.PRODUCTION;
    }
}

