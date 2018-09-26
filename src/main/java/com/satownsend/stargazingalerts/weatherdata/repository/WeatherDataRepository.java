package com.satownsend.stargazingalerts.weatherdata.repository;

import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WeatherDataRepository extends CrudRepository<WeatherData, Long> {
    Optional<WeatherData> findById(Long id);
}
