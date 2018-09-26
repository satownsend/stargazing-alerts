package com.satownsend.stargazingalerts.analyzer.service;

import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ForecastAnalyzerServiceTest {

    @Test
    public void isForecastGoodTestTrue() {

        ForecastAnalyzerService forecastAnalyzerService = new ForecastAnalyzerService();
        WeatherData weatherData = new WeatherData();
        weatherData.setCloudCover(0.1);
        weatherData.setPrecipProbability(0.01);
        weatherData.setTemperature(60.0);
        weatherData.setWindSpeed(4.0);

        Boolean result = forecastAnalyzerService.isForecastGood(weatherData);

        assertEquals(true, result);
    }

    @Test
    public void isForecastGoodTestFalse() {

        ForecastAnalyzerService fas = new ForecastAnalyzerService();
        WeatherData weatherData = new WeatherData();
        weatherData.setCloudCover(0.4);
        weatherData.setPrecipProbability(0.3);
        weatherData.setTemperature(20.0);
        weatherData.setWindSpeed(35.0);

        Boolean result = fas.isForecastGood(weatherData);

        assertEquals(false, result);

    }
}