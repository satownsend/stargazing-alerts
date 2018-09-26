package com.satownsend.stargazingalerts.darksky.model.forecast;

public class DarkSkyForecast {

    private Daily daily;
    private Hourly hourly;

    public DarkSkyForecast() {
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    @Override
    public String toString() {
        return "DarkSkyForecast{" +
                "daily=" + daily +
                ", hourly=" + hourly +
                '}';
    }

}