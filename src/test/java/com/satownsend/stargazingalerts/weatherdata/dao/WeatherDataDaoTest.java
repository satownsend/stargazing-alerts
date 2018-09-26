package com.satownsend.stargazingalerts.weatherdata.dao;

import com.satownsend.stargazingalerts.weatherdata.model.WeatherData;
import com.satownsend.stargazingalerts.weatherdata.repository.WeatherDataRepository;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherDataDaoTest {
    @Autowired
    private WeatherDataDao service;
    @MockBean
    private WeatherDataRepository repo;
    @TestConfiguration
    static class WeatherDataDaoTestContextConfiguration {
        @Bean
        @Primary
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder().setType(H2).build();
        }
    }
    @Test
    public void testFindById() {
        WeatherData weatherData = mock(WeatherData.class);
        Optional <WeatherData> op = Optional.of(weatherData);
        when(repo.findById(1L)).thenReturn(op);
        WeatherData result = service.findById(1L);
        verify(repo).findById(1L);

        assertThat(result).isEqualTo(weatherData);
    }
}