package com.satownsend.stargazingalerts;

import com.satownsend.stargazingalerts.alerter.sendgrid.SendgridConfigProperties;
import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Autowired
    private SendgridConfigProperties sendgridConfigProperties;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public SendGrid sendGrid() {
        return new SendGrid(sendgridConfigProperties.getApiKey());
    }

}