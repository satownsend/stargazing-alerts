package com.satownsend.stargazingalerts.darksky.service;

import com.satownsend.stargazingalerts.darksky.model.forecast.DarkSkyForecast;
import com.satownsend.stargazingalerts.darksky.model.forecast.HourlyData;
import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DarkSkyForecastConverterService {

    public Boolean checkTimesMatch(DarkSkyForecast darkSkyForecast) {

        if (darkSkyForecast.getHourly().getData().get(13).getTime() == darkSkyForecast.getHourly().getData().get(0).getTime() + 13 * 60 * 60)
        {
             return true;
        }
        return false;
    }

    public Boolean findMatchingTime(DarkSkyForecast darkSkyForecast) { //TODO: Should this return hourlyData and throw Exception if not found?

        List<HourlyData> hourlyDataList = darkSkyForecast.getHourly().getData();

        for (HourlyData hourlyData: hourlyDataList) {
            if (hourlyData.getTime() == darkSkyForecast.getHourly().getData().get(0).getTime() + 13 * 60 * 60) {
                System.out.println("Found the matching time");
                return true;
            }
        }
        System.out.println("Error: Unable to find matching times");
        return false;
    }

    public WeatherData convertForecast(DarkSkyForecast darkSkyForecast) {

        WeatherData weatherData = new WeatherData();
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