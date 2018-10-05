package com.satownsend.stargazingalerts.alerter.service;

import com.satownsend.stargazingalerts.alerter.model.Alert;
import com.satownsend.stargazingalerts.alerter.sendgrid.SendGridFactory;
import com.satownsend.stargazingalerts.user.model.User;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AlertService {

    @Autowired
    private SendGrid sendGrid;

    @Autowired
    private SendGridFactory sendGridFactory;

    public void sendAlert(Alert alert, User user) throws IOException {

        Email from = sendGridFactory.newEmail("nonreply@satownsend.com");
        String subject = "Stargazing Alert for " + user.getCity();
        Email to = sendGridFactory.newEmail(user.getEmail());
        Content content = sendGridFactory.newContent("text/plain", alert.getAlertMessage());
        Mail mail = sendGridFactory.newMail(from, subject, to, content);
        Request request = sendGridFactory.newRequest();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);

        } catch (IOException ex) {
            throw ex;
        }
    }
}