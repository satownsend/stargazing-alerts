package com.satownsend.stargazingalerts.weatherdata.service;

import com.satownsend.stargazingalerts.alerter.dao.AlertDao;
import com.satownsend.stargazingalerts.alerter.model.Alert;
import com.satownsend.stargazingalerts.alerter.model.AlertFactory;
import com.satownsend.stargazingalerts.alerter.service.AlertService;
import com.satownsend.stargazingalerts.analyzer.service.ForecastAnalyzerService;
import com.satownsend.stargazingalerts.darksky.model.forecast.DarkSkyForecast;
import com.satownsend.stargazingalerts.darksky.service.DarkSkyConsumerService;
import com.satownsend.stargazingalerts.darksky.service.DarkSkyForecastConverterService;
import com.satownsend.stargazingalerts.user.dao.UserDao;
import com.satownsend.stargazingalerts.user.model.User;
import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherDataServiceTest {

    @SpyBean
    private WeatherDataService weatherDataService;

    @MockBean
    private UserDao userDao;

    @MockBean
    private AlertDao alertDao;

    @MockBean
    private DarkSkyConsumerService darkSkyConsumerService;

    @MockBean
    private DarkSkyForecastConverterService converterService;

    @MockBean
    private ForecastAnalyzerService analyzerService;

    @MockBean
    private AlertService alertService;

    @MockBean
    private AlertFactory alertFactory;

    @TestConfiguration
    static class WeatherDataServiceTestContextConfiguration {

        @Bean
        @Primary
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder().setType(H2).build();
        }
    }

    @Test
    public void testRunAlert_whenTimesMatch() throws IOException {

        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getLatitude()).thenReturn(40.5);
        when(user.getLongitude()).thenReturn(-78.7);
        when(userDao.findById(1L)).thenReturn(user);

        DarkSkyForecast darkSkyForecast = mock(DarkSkyForecast.class);
        when(darkSkyConsumerService.findDarkSkyForecast(40.5, -78.7)).thenReturn(darkSkyForecast);
        when(converterService.checkTimesMatch(darkSkyForecast)).thenReturn(true);

        WeatherData weatherData = mock(WeatherData.class);
        when(converterService.convertForecast(darkSkyForecast)).thenReturn(weatherData);

        Alert alert = mock(Alert.class);
        when(alertFactory.newAlert()).thenReturn(alert);
        when(alert.getAlertMessage()).thenReturn("Tonight's forecast is looking good!");
        when(analyzerService.isForecastGood(weatherData)).thenReturn(true);

        weatherDataService.run(user);

        verify(userDao).findById(1L);
        verify(darkSkyConsumerService).findDarkSkyForecast(40.5, -78.7);
        verify(converterService).checkTimesMatch(darkSkyForecast);
        verify(converterService, never()).findMatchingTime(any(DarkSkyForecast.class));
        verify(converterService).convertForecast(darkSkyForecast);
        verify(analyzerService).isForecastGood(weatherData);
        verify(alertFactory).newAlert();
        verify(alert).setAlertMessage("Tonight's forecast is looking good!");
        verify(alertService).sendAlert(alert, user);

    }

    @Test
    public void testRunAlert_whenTimesDoNotMatch() throws IOException {

        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getLatitude()).thenReturn(40.5);
        when(user.getLongitude()).thenReturn(-78.7);
        when(userDao.findById(1L)).thenReturn(user);

        DarkSkyForecast darkSkyForecast = mock(DarkSkyForecast.class);
        when(darkSkyConsumerService.findDarkSkyForecast(40.5, -78.7)).thenReturn(darkSkyForecast);
        when(converterService.checkTimesMatch(darkSkyForecast)).thenReturn(false);
        when(converterService.findMatchingTime(darkSkyForecast)).thenReturn(true);

        WeatherData weatherData = mock(WeatherData.class);
        when(converterService.convertForecast(darkSkyForecast)).thenReturn(weatherData);

        Alert alert = mock(Alert.class);
        when(alertFactory.newAlert()).thenReturn(alert);
        when(alert.getAlertMessage()).thenReturn("Tonight's forecast is looking good!");
        when(analyzerService.isForecastGood(weatherData)).thenReturn(true);

        weatherDataService.run(user);

        verify(userDao).findById(1L);
        verify(darkSkyConsumerService).findDarkSkyForecast(40.5, -78.7);
        verify(converterService).checkTimesMatch(darkSkyForecast);
        verify(converterService).findMatchingTime(darkSkyForecast);
        verify(converterService).convertForecast(darkSkyForecast);
        verify(analyzerService).isForecastGood(weatherData);
        verify(alertFactory).newAlert();
        verify(alert).setAlertMessage("Tonight's forecast is looking good!");
        verify(alertService).sendAlert(alert, user);

    }

    @Test
    public void testRunNoAlert() throws IOException {

        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getLatitude()).thenReturn(40.5);
        when(user.getLongitude()).thenReturn(-78.7);
        when(userDao.findById(1L)).thenReturn(user);

        DarkSkyForecast darkSkyForecast = mock(DarkSkyForecast.class);
        when(darkSkyConsumerService.findDarkSkyForecast(40.5, -78.7)).thenReturn(darkSkyForecast);
        when(converterService.checkTimesMatch(darkSkyForecast)).thenReturn(true);

        WeatherData weatherData = mock(WeatherData.class);
        when(converterService.convertForecast(darkSkyForecast)).thenReturn(weatherData);

        when(analyzerService.isForecastGood(weatherData)).thenReturn(false);

        weatherDataService.run(user);

        verify(userDao).findById(1L);
        verify(darkSkyConsumerService).findDarkSkyForecast(40.5, -78.7);
        verify(converterService).checkTimesMatch(darkSkyForecast);
        verify(converterService, never()).findMatchingTime(any(DarkSkyForecast.class));
        verify(converterService).convertForecast(darkSkyForecast);
        verify(analyzerService).isForecastGood(weatherData);
        verify(alertService, never()).sendAlert(any(Alert.class), any(User.class));

    }

    @Test
    public void testRunTimeMismatch_whenFound() throws IOException {

        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getLatitude()).thenReturn(40.5);
        when(user.getLongitude()).thenReturn(-78.7);
        when(userDao.findById(1L)).thenReturn(user);

        DarkSkyForecast darkSkyForecast = mock(DarkSkyForecast.class);
        when(darkSkyConsumerService.findDarkSkyForecast(40.5, -78.7)).thenReturn(darkSkyForecast);
        when(converterService.checkTimesMatch(darkSkyForecast)).thenReturn(false);
        when(converterService.findMatchingTime(darkSkyForecast)).thenReturn(true);

        WeatherData weatherData = mock(WeatherData.class);
        when(converterService.convertForecast(darkSkyForecast)).thenReturn(weatherData);

        Alert alert = mock(Alert.class);
        when(alertFactory.newAlert()).thenReturn(alert);
        when(alert.getAlertMessage()).thenReturn("Tonight's forecast is looking good!");
        when(analyzerService.isForecastGood(weatherData)).thenReturn(true);

        weatherDataService.run(user);

        verify(userDao).findById(1L);
        verify(darkSkyConsumerService).findDarkSkyForecast(40.5, -78.7);
        verify(converterService).checkTimesMatch(darkSkyForecast);
        verify(converterService).findMatchingTime(darkSkyForecast);
        verify(converterService).convertForecast(darkSkyForecast);
        verify(alertFactory).newAlert();
        verify(analyzerService).isForecastGood(weatherData);
        verify(alert).setAlertMessage("Tonight's forecast is looking good!");
        verify(alertService).sendAlert(alert, user);

    }

    @Test
    public void testRunTimeMismatch_whenNotFound() throws IOException {

        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getLatitude()).thenReturn(40.5);
        when(user.getLongitude()).thenReturn(-78.7);
        when(userDao.findById(1L)).thenReturn(user);

        DarkSkyForecast darkSkyForecast = mock(DarkSkyForecast.class);
        when(darkSkyConsumerService.findDarkSkyForecast(40.5, -78.7)).thenReturn(darkSkyForecast);
        when(converterService.checkTimesMatch(darkSkyForecast)).thenReturn(false);
        when(converterService.findMatchingTime(darkSkyForecast)).thenReturn(false);

        weatherDataService.run(user);

        verify(userDao).findById(1L);
        verify(darkSkyConsumerService).findDarkSkyForecast(40.5, -78.7);
        verify(converterService).checkTimesMatch(darkSkyForecast);
        verify(converterService).findMatchingTime(darkSkyForecast);
      //  verify(converterService, never()).convertForecast(any(DarkSkyForecast.class));  Uncomment when convertForecast is not called when matching time isn't found.
        verify(alertService, never()).sendAlert(any(Alert.class), any(User.class));

    }

    @Test
    public void testRunForAllUsers() throws IOException {

        List<User> users = new ArrayList<User>();

        User user1 = mock(User.class);
        users.add(user1);

        User user2 = mock(User.class);
        users.add(user2);

        when(userDao.findAll()).thenReturn(users);
        Mockito.doNothing().when(weatherDataService).run(user1);
        Mockito.doNothing().when(weatherDataService).run(user2);
        weatherDataService.runForAllUsers();
        verify(userDao).findAll();
        verify(weatherDataService).run(user1);
        verify(weatherDataService).run(user2);

    }
}