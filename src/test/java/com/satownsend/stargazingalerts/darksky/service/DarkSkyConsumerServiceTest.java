package com.satownsend.stargazingalerts.darksky.service;

import com.satownsend.stargazingalerts.darksky.DarkSkyConfigProperties;
import com.satownsend.stargazingalerts.darksky.model.forecast.DarkSkyForecast;
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
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DarkSkyConsumerServiceTest {

    @Autowired
    private DarkSkyConsumerService service;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean private DarkSkyConfigProperties darkSky;

    @TestConfiguration
    static class DarkSkyConsumerServiceTestContextConfiguration {

        @Bean
        @Primary
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder().setType(H2).build();
        }
    }
    @Test
    public void testFindDarkSkyForecast() {

        DarkSkyForecast dsf = mock(DarkSkyForecast.class);
        when(restTemplate.getForObject("https://api.darksky.net/forecast/" + darkSky.getApiKey() + "/40.4,-78.7",
                DarkSkyForecast.class)).thenReturn(dsf);
        DarkSkyForecast result = service.findDarkSkyForecast(40.4,-78.7);
        verify(restTemplate).getForObject("https://api.darksky.net/forecast/" + darkSky.getApiKey() + "/40.4,-78.7",
                DarkSkyForecast.class);

        assertThat(result).isEqualTo(dsf);

    }
}