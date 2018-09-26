package com.satownsend.stargazingalerts.user.dao;

import com.satownsend.stargazingalerts.user.model.User;
import com.satownsend.stargazingalerts.user.repository.UserRepository;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao service;
    @MockBean
    private UserRepository repo;
    @TestConfiguration
    static class UserDaoTestContextConfiguration {
        @Bean
        @Primary
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder().setType(H2).build();
        }
    }
    @Test
    public void testFindById() {
        User user = mock(User.class);
        Optional <User> op = Optional.of(user);
        when(repo.findById(1L)).thenReturn(op);
        User result = service.findById(1L);
        verify(repo).findById(1L);

        assertThat(result).isEqualTo(user);
    }
    @Test
    public void testFindAll() {
        List<User> users = mock(List.class);
        when(repo.findAll()).thenReturn(users);
        List<User> result = service.findAll();
        verify(repo).findAll();

        assertThat(result).isEqualTo(users);
    }
}
