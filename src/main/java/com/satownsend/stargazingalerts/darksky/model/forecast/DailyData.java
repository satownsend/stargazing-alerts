package com.satownsend.stargazingalerts.darksky.model.forecast;

public class DailyData {

    private Double moonPhase;
    private Integer time;
    private Integer sunriseTime;
    private Integer sunsetTime;

    public DailyData() {
    }

    public Double getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(Double moonPhase) {
        this.moonPhase = moonPhase;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(Integer sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public Integer getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(Integer sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    @Override
    public String toString() {
        return "DailyData{" +
                "moonPhase=" + moonPhase +
                ", time=" + time +
                ", sunriseTime=" + sunriseTime +
                ", sunsetTime=" + sunsetTime +
                '}';
    }
}
