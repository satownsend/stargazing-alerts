package com.satownsend.stargazingalerts.weatherdata.model;

import org.springframework.stereotype.Service;

@Service
public class WeatherDataFactory {

    public WeatherData newWeatherData() {
        return new WeatherData();
    }
}
