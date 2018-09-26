package com.satownsend.stargazingalerts.darksky.model.forecast;

public class HourlyData {

    private Double cloudCover;
    private Double precipProbability;
    private Double temperature;
    private Double windSpeed;
    private Integer time;

    public HourlyData() {
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "HourlyData{" +
                "cloudCover=" + cloudCover +
                ", precipProbability=" + precipProbability +
                ", temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                ", time=" + time +
                '}';
    }
}
