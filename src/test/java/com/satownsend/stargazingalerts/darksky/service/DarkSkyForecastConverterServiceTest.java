package com.satownsend.stargazingalerts.darksky.service;

import com.satownsend.stargazingalerts.darksky.model.forecast.*;
import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DarkSkyForecastConverterServiceTest {

    @Autowired
    private DarkSkyForecastConverterService service;

    @TestConfiguration
    static class DarkSkyForecastConverterServiceTestContextConfiguration {
        @Bean
        @Primary
        public DataSource dataSource() {
                return new EmbeddedDatabaseBuilder().setType(H2).build();
            }
    }
    @Test
    public void testCheckTimesMatch_whenMatch() {

        DarkSkyForecast darkSkyForecast = new DarkSkyForecast();
        Hourly hourly = new Hourly();
        HourlyData hourlyData1 = new HourlyData();
        HourlyData hourlyData2 = new HourlyData();
        HourlyData hourlyData3 = new HourlyData();
        HourlyData hourlyData4 = new HourlyData();
        HourlyData hourlyData5 = new HourlyData();
        HourlyData hourlyData6 = new HourlyData();
        HourlyData hourlyData7 = new HourlyData();
        HourlyData hourlyData8 = new HourlyData();
        HourlyData hourlyData9 = new HourlyData();
        HourlyData hourlyData10 = new HourlyData();
        HourlyData hourlyData11 = new HourlyData();
        HourlyData hourlyData12 = new HourlyData();
        HourlyData hourlyData13 = new HourlyData();
        HourlyData hourlyData14 = new HourlyData();
        List<HourlyData> hourlyDataList = new ArrayList<>();
        hourlyDataList.add(hourlyData1);
        hourlyDataList.add(hourlyData2);
        hourlyDataList.add(hourlyData3);
        hourlyDataList.add(hourlyData4);
        hourlyDataList.add(hourlyData5);
        hourlyDataList.add(hourlyData6);
        hourlyDataList.add(hourlyData7);
        hourlyDataList.add(hourlyData8);
        hourlyDataList.add(hourlyData9);
        hourlyDataList.add(hourlyData10);
        hourlyDataList.add(hourlyData11);
        hourlyDataList.add(hourlyData12);
        hourlyDataList.add(hourlyData13);
        hourlyDataList.add(hourlyData14);

        darkSkyForecast.setHourly(hourly);
        hourly.setData(hourlyDataList);
        hourlyData1.setTime(1525788000);
        hourlyData14.setTime(1525788000 + 13 * 60 * 60);

        Boolean result = service.checkTimesMatch(darkSkyForecast);

        assertThat(result).isEqualTo(true);

    }

    @Test
    public void testCheckTimesMatch_whenMismatch() {

        DarkSkyForecast darkSkyForecast = new DarkSkyForecast();
        Hourly hourly = new Hourly();
        HourlyData hourlyData1 = new HourlyData();
        HourlyData hourlyData2 = new HourlyData();
        HourlyData hourlyData3 = new HourlyData();
        HourlyData hourlyData4 = new HourlyData();
        HourlyData hourlyData5 = new HourlyData();
        HourlyData hourlyData6 = new HourlyData();
        HourlyData hourlyData7 = new HourlyData();
        HourlyData hourlyData8 = new HourlyData();
        HourlyData hourlyData9 = new HourlyData();
        HourlyData hourlyData10 = new HourlyData();
        HourlyData hourlyData11 = new HourlyData();
        HourlyData hourlyData12 = new HourlyData();
        HourlyData hourlyData13 = new HourlyData();
        HourlyData hourlyData14 = new HourlyData();
        List<HourlyData> hourlyDataList = new ArrayList<>();
        hourlyDataList.add(hourlyData1);
        hourlyDataList.add(hourlyData2);
        hourlyDataList.add(hourlyData3);
        hourlyDataList.add(hourlyData4);
        hourlyDataList.add(hourlyData5);
        hourlyDataList.add(hourlyData6);
        hourlyDataList.add(hourlyData7);
        hourlyDataList.add(hourlyData8);
        hourlyDataList.add(hourlyData9);
        hourlyDataList.add(hourlyData10);
        hourlyDataList.add(hourlyData11);
        hourlyDataList.add(hourlyData12);
        hourlyDataList.add(hourlyData13);
        hourlyDataList.add(hourlyData14);

        darkSkyForecast.setHourly(hourly);
        hourly.setData(hourlyDataList);
        hourlyData1.setTime(1525788000);
        hourlyData14.setTime(1525788000 + 12 * 60 * 60);

        Boolean result = service.checkTimesMatch(darkSkyForecast);

        assertThat(result).isEqualTo(false);

    }

    @Test
    public void testFindMatchingTime() {
        // TODO
        // Incomplete. Started to play around on my lunch, but ran out of time.
        DarkSkyForecast darkSkyForecast = mock(DarkSkyForecast.class);

        HourlyData hourlyData1 = new HourlyData();
        HourlyData hourlyData2 = new HourlyData();
        HourlyData hourlyData3 = new HourlyData();
        HourlyData hourlyData4 = new HourlyData();
        HourlyData hourlyData5 = new HourlyData();
        HourlyData hourlyData6 = new HourlyData();
        HourlyData hourlyData7 = new HourlyData();
        HourlyData hourlyData8 = new HourlyData();
        HourlyData hourlyData9 = new HourlyData();
        HourlyData hourlyData10 = new HourlyData();
        HourlyData hourlyData11 = new HourlyData();
        HourlyData hourlyData12 = new HourlyData();
        HourlyData hourlyData13 = new HourlyData();
        HourlyData hourlyData14 = new HourlyData();
        hourlyData14.setTime(1200);
        List<HourlyData> hourlyDataList = new ArrayList<>();
        hourlyDataList.add(hourlyData1);
        hourlyDataList.add(hourlyData2);
        hourlyDataList.add(hourlyData3);
        hourlyDataList.add(hourlyData4);
        hourlyDataList.add(hourlyData5);
        hourlyDataList.add(hourlyData6);
        hourlyDataList.add(hourlyData7);
        hourlyDataList.add(hourlyData8);
        hourlyDataList.add(hourlyData9);
        hourlyDataList.add(hourlyData10);
        hourlyDataList.add(hourlyData11);
        hourlyDataList.add(hourlyData12);
        hourlyDataList.add(hourlyData13);
        hourlyDataList.add(hourlyData14);

        when(darkSkyForecast.getHourly().getData().get(13)).thenReturn(hourlyData14);

        Boolean result = service.findMatchingTime(darkSkyForecast);

        assertThat(result).isEqualTo(true);

    }
    /*
        public Boolean findMatchingTime(DarkSkyForecast darkSkyForecast) {

        List<HourlyData> hourlyDataList = darkSkyForecast.getHourly().getData();

        for (HourlyData hourlyData: hourlyDataList) {
            if (hourlyData.getTime() == darkSkyForecast.getHourly().getData().get(0).getTime() + 13 * 60 * 60) {
                System.out.println("Found the matching time");
                return true;
            }
        }
        System.out.println("Error: Unable to find matching times");
        return false;
    }
     */
    @Test
    public void testConvertForecast() { //TODO

        DarkSkyForecast darkSkyForecast = new DarkSkyForecast();

        Daily daily = new Daily();
        darkSkyForecast.setDaily(daily);
        List<DailyData> dData = new ArrayList<DailyData>();
        daily.setData(dData);
        DailyData dailyData = new DailyData();
        dData.add(dailyData);
        dailyData.setMoonPhase(0.5);

        Hourly hourly = new Hourly();
        darkSkyForecast.setHourly(hourly);
        List<HourlyData> hData = mock(List.class);
        hourly.setData(hData);
        HourlyData hourlyData = new HourlyData();
        when(hData.get(12)).thenReturn(hourlyData);
        hourlyData.setTime(12);
        hData.add(hourlyData);
        hourlyData.setCloudCover(0.1);
        hData.add(hourlyData);
        hourlyData.setPrecipProbability(0.02);
        hData.add(hourlyData);
        hourlyData.setTemperature(50.0);
        hData.add(hourlyData);
        hourlyData.setWindSpeed(10.0);

        WeatherData result = service.convertForecast(darkSkyForecast);
        assertThat(result.getMoonPhase()).isEqualTo(0.5);
        assertThat(result.getCloudCover()).isEqualTo(0.1);
        assertThat(result.getPrecipProbability()).isEqualTo(0.02);
        assertThat(result.getTemperature()).isEqualTo(50.0);
        assertThat(result.getWindSpeed()).isEqualTo(10.0);
        assertThat(result.getForecastDataTime()).isEqualTo(12);
    }
}
/*

    public WeatherData convertForecast(DarkSkyForecast darkSkyForecast) {

        WeatherData weatherData = new WeatherData();
        weatherData.setMoonPhase(darkSkyForecast.getDaily().getData().get(0).getMoonPhase());

        HourlyData hourlyData = darkSkyForecast.getHourly().getData().get(13);

        weatherData.setForecastDataTime(hourlyData.getTime());
        weatherData.setCloudCover(hourlyData.getCloudCover());
        weatherData.setPrecipProbability(hourlyData.getPrecipProbability());
        weatherData.setTemperature(hourlyData.getTemperature());
        weatherData.setWindSpeed(hourlyData.getWindSpeed());

        return weatherData;
    }
}
 */