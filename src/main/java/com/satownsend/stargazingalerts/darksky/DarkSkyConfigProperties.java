package com.satownsend.stargazingalerts.darksky;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "darksky")
public class DarkSkyConfigProperties {

    private String apiKey;

    public DarkSkyConfigProperties() {
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}