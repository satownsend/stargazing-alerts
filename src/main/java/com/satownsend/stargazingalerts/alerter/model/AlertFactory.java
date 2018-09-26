package com.satownsend.stargazingalerts.alerter.model;

import org.springframework.stereotype.Service;

@Service
public class AlertFactory {

    public Alert newAlert() {
        return new Alert();
    }
}