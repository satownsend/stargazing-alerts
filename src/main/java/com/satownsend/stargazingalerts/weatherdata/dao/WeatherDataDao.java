package com.satownsend.stargazingalerts.weatherdata.dao;

import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import com.satownsend.stargazingalerts.weatherdata.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataDao {

    @Autowired
    private WeatherDataRepository repo;

    public WeatherData findById(Long id) {
        return repo.findById(id).get();
    }

}
