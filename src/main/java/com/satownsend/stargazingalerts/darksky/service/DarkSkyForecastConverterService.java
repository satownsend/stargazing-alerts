package com.satownsend.stargazingalerts.darksky.service;

import com.satownsend.stargazingalerts.darksky.model.forecast.DarkSkyForecast;
import com.satownsend.stargazingalerts.darksky.model.forecast.HourlyData;
import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import com.satownsend.stargazingalerts.weatherdata.model.WeatherDataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DarkSkyForecastConverterService {

    @Autowired private WeatherDataFactory weatherDataFactory;

    public Boolean checkTimesMatch(DarkSkyForecast darkSkyForecast) {

        if (darkSkyForecast.getHourly().getData().get(13).getTime() == darkSkyForecast.getHourly().getData().get(0).getTime() + 13 * 60 * 60)
        {
             return true;
        }
        return false;
    }

    public Boolean findMatchingTime(DarkSkyForecast darkSkyForecast) throws Exception { //TODO: throw Exception and test

        List<HourlyData> hourlyDataList = darkSkyForecast.getHourly().getData();

        for (HourlyData hourlyData: hourlyDataList) {
            if (hourlyData.getTime() == darkSkyForecast.getHourly().getData().get(0).getTime() + 13 * 60 * 60) {
                System.out.println("Found the matching time");
                return true;
            }
        }
        throw new Exception("Error: Unable to find matching times");

    }

    public WeatherData convertForecast(DarkSkyForecast darkSkyForecast) {

        WeatherData weatherData = weatherDataFactory.newWeatherData();
        weatherData.setMoonPhase(darkSkyForecast.getDaily().getData().get(0).getMoonPhase());

        HourlyData hourlyData = darkSkyForecast.getHourly().getData().get(13);

        weatherData.setForecastDataTime(hourlyData.getTime());
        weatherData.setCloudCover(hourlyData.getCloudCover());
        weatherData.setPrecipProbability(hourlyData.getPrecipProbability());
        weatherData.setTemperature(hourlyData.getTemperature());
        weatherData.setWindSpeed(hourlyData.getWindSpeed());

        return weatherData;
    }
}