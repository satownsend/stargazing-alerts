package com.satownsend.stargazingalerts.darksky.model.forecast;

import java.util.List;

public class Daily {

    private List<DailyData> data;

    public Daily() {
    }

    public List<DailyData> getData() {
        return data;
    }

    public void setData(List<DailyData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Daily{" +
                "data=" + data +
                '}';
    }
}
