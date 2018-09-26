package com.satownsend.stargazingalerts.weatherdata.model;

import javax.persistence.*;

@Entity
public class WeatherData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "forecastDataTime")
    private Integer forecastDataTime;

    @Column(name = "cloudCover")
    private Double cloudCover;

    @Column(name = "moonPhase")
    private Double moonPhase;

    @Column(name = "precipProbability")
    private Double precipProbability;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "windSpeed")
    private Double windSpeed;

    public WeatherData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getForecastDataTime() {
        return forecastDataTime;
    }

    public void setForecastDataTime(Integer forecastDataTime) {
        this.forecastDataTime = forecastDataTime;
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Double getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(Double moonPhase) {
        this.moonPhase = moonPhase;
    }

    public Double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
