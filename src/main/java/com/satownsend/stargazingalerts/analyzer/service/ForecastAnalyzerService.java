package com.satownsend.stargazingalerts.analyzer.service;

import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import org.springframework.stereotype.Service;

@Service
public class ForecastAnalyzerService {

    public Boolean isForecastGood(WeatherData weatherData) {

        return weatherData.getCloudCover() < 0.2 && weatherData.getPrecipProbability() < 0.05
                && weatherData.getTemperature() > 25.0 && weatherData.getWindSpeed() < 30.0;
    }

}