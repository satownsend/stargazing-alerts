package com.satownsend.stargazingalerts.weatherdata.service;

import com.satownsend.stargazingalerts.alerter.dao.AlertDao;
import com.satownsend.stargazingalerts.alerter.model.Alert;
import com.satownsend.stargazingalerts.alerter.model.AlertFactory;
import com.satownsend.stargazingalerts.alerter.service.AlertService;
import com.satownsend.stargazingalerts.analyzer.service.ForecastAnalyzerService;
import com.satownsend.stargazingalerts.darksky.model.forecast.DarkSkyForecast;
import com.satownsend.stargazingalerts.darksky.service.DarkSkyConsumerService;
import com.satownsend.stargazingalerts.darksky.service.DarkSkyForecastConverterService;
import com.satownsend.stargazingalerts.user.dao.UserDao;
import com.satownsend.stargazingalerts.user.model.User;
import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherDataService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AlertDao alertDao;

    @Autowired
    private DarkSkyConsumerService darkSkyConsumerService;

    @Autowired
    private DarkSkyForecastConverterService converterService;

    @Autowired
    private ForecastAnalyzerService analyzerService;

    @Autowired
    private AlertService alertService;

    @Autowired
    private AlertFactory alertFactory;

    @Scheduled(cron = "0 0 9 * * *")
    public void runForAllUsers() throws IOException {

        for (User user: userDao.findAll()) run(user);
    }

    public void run(User user) throws IOException {

        System.out.println("About to look up user");
        User u = userDao.findById(user.getId());

        System.out.println("Finding Dark Sky Forecast");
        DarkSkyForecast darkSkyForecast = darkSkyConsumerService.findDarkSkyForecast(u.getLatitude(), u.getLongitude());

        System.out.println("Checking if times match");
        if (!converterService.checkTimesMatch(darkSkyForecast)) {
            System.out.println("Times do not match");
            converterService.findMatchingTime(darkSkyForecast);
        }
        //TODO: convertForecast is called even if the matching time isn't found. Making findMatchingTime throw an exception if not found should fix this.
        System.out.println("Converting forecast");
        WeatherData weatherData = converterService.convertForecast(darkSkyForecast);

        System.out.println("Checking if forecast is good");
        if (analyzerService.isForecastGood(weatherData)) {
            Alert alert = alertFactory.newAlert();
            alert.setAlertMessage("Tonight's forecast is looking good!");
            alertService.sendAlert(alert, user);
            System.out.println("Forecast is good!");
        } else {
            System.out.println("Not good.");
        }
    }
}