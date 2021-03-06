package com.satownsend.stargazingalerts.weatherdata.service;

import com.satownsend.stargazingalerts.alerter.dao.AlertDao;
import com.satownsend.stargazingalerts.alerter.model.Alert;
import com.satownsend.stargazingalerts.alerter.model.AlertFactory;
import com.satownsend.stargazingalerts.alerter.service.AlertService;
import com.satownsend.stargazingalerts.analyzer.service.ForecastAnalyzerService;
import com.satownsend.stargazingalerts.darksky.model.forecast.DarkSkyForecast;
import com.satownsend.stargazingalerts.darksky.service.DarkSkyConsumerService;
import com.satownsend.stargazingalerts.darksky.service.DarkSkyForecastConverterService;
import com.satownsend.stargazingalerts.geocoder.service.GeocoderService;
import com.satownsend.stargazingalerts.user.dao.UserDao;
import com.satownsend.stargazingalerts.user.model.User;
import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

    @Autowired
    private GeocoderService geocodingService;

    @Scheduled(cron = "0 0 9 * * *") // Timer set to run everyday at 9 am. I'll eventually change to later in the day.
    public void runForAllUsers() throws Exception {

        for (User user: userDao.findAll()) run(user);
    }

    public void run(User user) throws Exception {

        System.out.println("Finding Dark Sky Forecast");
        DarkSkyForecast darkSkyForecast = darkSkyConsumerService.findDarkSkyForecast(user.getLatitude(),
                user.getLongitude());

        System.out.println("Checking if times match");
        if (!converterService.checkTimesMatch(darkSkyForecast)) {

            System.out.println("Times do not match");
            converterService.findMatchingTime(darkSkyForecast);
        }

        System.out.println("Converting forecast");
        WeatherData weatherData = converterService.convertForecast(darkSkyForecast);
        converterService.translateMoonPhase(weatherData);
        System.out.println("Moon phase: " + weatherData.getNameOfMoonPhase());

        System.out.println("Checking if forecast is good");
        if (analyzerService.isForecastGood(weatherData)) {

            Alert alert = alertFactory.newAlert();
            alert.setAlertMessage("Hey " + user.getName() + "! Tonight's forecast in " + user.getCity() +
                    " is looking good!  Tonight's moon phase: " + weatherData.getNameOfMoonPhase() + ".  Check out a " +
                    "detailed weather forecast here: https://darksky.net/forecast/" + user.getLatitude() + "," +
                    user.getLongitude() + "/us12/en");

            alertService.sendAlert(alert, user);

            System.out.println("Forecast is good");

        } else {
            System.out.println("Not good");
        }
    }
}
