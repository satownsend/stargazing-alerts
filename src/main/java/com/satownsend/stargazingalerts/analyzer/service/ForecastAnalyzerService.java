package com.satownsend.stargazingalerts.analyzer.service;

import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import org.springframework.stereotype.Service;

/* I misinterpreted the moonPhase data. I was looking at it as a percent of the moon illuminated (0.00 = new,
 0.5 = quarter, 1.00 = full), but it's really 0.00 = new, 0.25 = first quarter, 0.50 = full, 0.75 = last quarter.
 My initial plan was to only send an alert when the light from the moon was minimal, but I think I want to send during
 all phases. A future enhancement will be to send the moon phase with the alert, possibly suggesting the type of viewing
 that is ideal for the conditions.
*/
@Service
public class ForecastAnalyzerService {

    public Boolean isForecastGood(WeatherData weatherData) {

        return weatherData.getCloudCover() < 0.25 && weatherData.getPrecipProbability() < 0.05
                && weatherData.getTemperature() > 25.0 && weatherData.getWindSpeed() < 30.0;
    }

}