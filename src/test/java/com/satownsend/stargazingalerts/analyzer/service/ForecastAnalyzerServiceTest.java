package com.satownsend.stargazingalerts.analyzer.service;

import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ForecastAnalyzerServiceTest {

    @Test
    public void isForecastGoodTestTrue() { //TODO

        ForecastAnalyzerService fas = new ForecastAnalyzerService();
        WeatherData weatherData = new WeatherData();
        weatherData.setCloudCover(0.1);
        weatherData.setMoonPhase(0.3);

        Boolean result = fas.isForecastGood(weatherData);

        assertEquals(true, result);
    }
    @Test
    public void isForecastGoodTestFalse() {

        ForecastAnalyzerService fas = new ForecastAnalyzerService();
        WeatherData weatherData = new WeatherData();
        weatherData.setCloudCover(0.6);
        weatherData.setMoonPhase(0.9);

        Boolean result = fas.isForecastGood(weatherData);

        assertEquals(false, result);

    }
}