package com.satownsend.stargazingalerts.darksky.service;

import com.satownsend.stargazingalerts.darksky.DarkSkyConfigProperties;
import com.satownsend.stargazingalerts.darksky.model.forecast.DarkSkyForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class DarkSkyConsumerService {

    @Autowired
    private RestOperations restTemplate;

    @Autowired private DarkSkyConfigProperties darkSky;

    public DarkSkyForecast findDarkSkyForecast(Double latitude, Double longitude) {

        DarkSkyForecast darkSkyForecast = restTemplate.getForObject("https://api.darksky.net/forecast/" +
                darkSky.getApiKey() + "/" + latitude + "," + longitude, DarkSkyForecast.class);

        return darkSkyForecast;
    }

    public RestOperations getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }
}