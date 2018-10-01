package com.satownsend.stargazingalerts.alerter.service;

import com.satownsend.stargazingalerts.alerter.model.Alert;
import com.satownsend.stargazingalerts.alerter.sendgrid.SendGridFactory;
import com.satownsend.stargazingalerts.user.model.User;
import com.sendgrid.*;
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
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlertServiceTest {

    @Autowired
    AlertService service;

    @MockBean
    private SendGrid sendGrid;

    @MockBean
    private SendGridFactory sendGridFactory;

    @TestConfiguration
    static class AlertServiceTestContextConfiguration {

        @Bean
        @Primary
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder().setType(H2).build();
        }
    }
    @Test
    public void testSendAlert() throws IOException {

        User user = mock(User.class);
        when(user.getEmail()).thenReturn("test@mail.com");
        when(user.getName()).thenReturn("Scott");

        Email from = mock(Email.class);
        when(sendGridFactory.newEmail("nonreply@satownsend.com")).thenReturn(from);

        String subject = "Stargazing Alert";

        Email to = mock(Email.class);
        when(sendGridFactory.newEmail("test@mail.com")).thenReturn(to);

        Alert alert = mock(Alert.class);
        when(alert.getAlertMessage()).thenReturn("Hi Scott! The sky is clear.");

        Content content = mock(Content.class);
        when(sendGridFactory.newContent("text/plain", alert.getAlertMessage())).thenReturn(content);

        Mail mail = mock(Mail.class);
        when(sendGridFactory.newMail(from, subject, to, content)).thenReturn(mail);


        Request request = mock(Request.class);
        when(sendGridFactory.newRequest()).thenReturn(request);
        when(mail.build()).thenReturn("The body");

        service.sendAlert(alert, user);
        verify(sendGridFactory).newEmail("nonreply@satownsend.com");
        verify(sendGridFactory).newEmail("test@mail.com");
        verify(sendGridFactory).newContent("text/plain", "Hi " + user.getName() + "! The sky is clear.");
        verify(sendGridFactory).newMail(from, subject, to, content);
        verify(sendGridFactory).newRequest();
        verify(request).setMethod(Method.POST);
        verify(request).setEndpoint("mail/send");
        verify(request).setBody("The body");
        verify(sendGrid).api(request);

    }
}