package com.satownsend.stargazingalerts.alerter.sendgrid;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Request;
import org.springframework.stereotype.Service;

@Service
public class SendGridFactory {

    public Email newEmail(String email) {
        return new Email(email);
    }

    public Content newContent(String type, String alertMessage) {
        return new Content(type, alertMessage);
    }

    public Mail newMail(Email from, String subject, Email to, Content content) {
        return new Mail(from, subject, to, content);
    }

    public Request newRequest() {
        return new Request();
    }
}
