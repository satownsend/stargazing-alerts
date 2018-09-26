package com.satownsend.stargazingalerts.darksky.model.forecast;

import java.util.List;

public class Hourly {

    private String summary;
    private List<HourlyData> data;


    public Hourly() {
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<HourlyData> getData() {
        return data;
    }

    public void setData(List<HourlyData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Hourly{" +
                "summary='" + summary + '\'' +
                ", data=" + data +
                '}';
    }
}