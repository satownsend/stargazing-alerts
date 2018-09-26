package com.satownsend.stargazingalerts.darksky.service;

import com.satownsend.stargazingalerts.darksky.model.forecast.*;
import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import com.satownsend.stargazingalerts.weatherdata.model.WeatherDataFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @MockBean private WeatherDataFactory weatherDataFactory;

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
    public void testFindMatchingTime_whenFound() throws Exception {
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
    public Boolean findMatchingTime(DarkSkyForecast darkSkyForecast) throws Exception {

        List<HourlyData> hourlyDataList = darkSkyForecast.getHourly().getData();

        for (HourlyData hourlyData: hourlyDataList) {
            if (hourlyData.getTime() == darkSkyForecast.getHourly().getData().get(0).getTime() + 13 * 60 * 60) {
                System.out.println("Found the matching time");
                return true;
            }
        }
        throw new Exception("Error: Unable to find matching times");

    }
     */

    @Test
    public void testFindMatchingTime_whenUnableToFind() throws Exception {

        //TODO
    }

    @Test
    public void testConvertForecast() {

        DarkSkyForecast darkSkyForecast = mock(DarkSkyForecast.class);

        Daily daily = mock(Daily.class);
        when(darkSkyForecast.getDaily()).thenReturn(daily);

        List<DailyData> dailyDataList = new ArrayList<>();
        when(daily.getData()).thenReturn(dailyDataList);

        DailyData dailyData = mock(DailyData.class);
        when(dailyData.getMoonPhase()).thenReturn(0.5);
        dailyDataList.add(dailyData);

        Hourly hourly = mock(Hourly.class);
        when(darkSkyForecast.getHourly()).thenReturn(hourly);

        List<HourlyData> hourlyDataList = new ArrayList<>();
        hourly.setData(hourlyDataList);

        HourlyData hourlyData0 = mock(HourlyData.class);
        HourlyData hourlyData1 = mock(HourlyData.class);
        HourlyData hourlyData2 = mock(HourlyData.class);
        HourlyData hourlyData3 = mock(HourlyData.class);
        HourlyData hourlyData4 = mock(HourlyData.class);
        HourlyData hourlyData5 = mock(HourlyData.class);
        HourlyData hourlyData6 = mock(HourlyData.class);
        HourlyData hourlyData7 = mock(HourlyData.class);
        HourlyData hourlyData8 = mock(HourlyData.class);
        HourlyData hourlyData9 = mock(HourlyData.class);
        HourlyData hourlyData10 = mock(HourlyData.class);
        HourlyData hourlyData11 = mock(HourlyData.class);
        HourlyData hourlyData12 = mock(HourlyData.class);
        HourlyData hourlyData13 = mock(HourlyData.class);

        hourlyDataList.add(hourlyData0);
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

        when(hourly.getData()).thenReturn(hourlyDataList);

        WeatherData weatherData = mock(WeatherData.class);
        when(weatherDataFactory.newWeatherData()).thenReturn(weatherData);
        when(weatherData.getMoonPhase()).thenReturn(0.5);
        when(weatherData.getForecastDataTime()).thenReturn(1525788663);
        when(weatherData.getCloudCover()).thenReturn(0.05);
        when(weatherData.getPrecipProbability()).thenReturn(0.01);
        when(weatherData.getTemperature()).thenReturn(60.0);
        when(weatherData.getWindSpeed()).thenReturn(5.0);

        WeatherData result = service.convertForecast(darkSkyForecast);

        assertThat(result.getMoonPhase()).isEqualTo(0.5);
        assertThat(result.getCloudCover()).isEqualTo(0.05);
        assertThat(result.getPrecipProbability()).isEqualTo(0.01);
        assertThat(result.getTemperature()).isEqualTo(60.0);
        assertThat(result.getWindSpeed()).isEqualTo(5.0);
        assertThat(result.getForecastDataTime()).isEqualTo(1525788663);
        assertThat(result).isEqualTo(weatherData);

    }
}