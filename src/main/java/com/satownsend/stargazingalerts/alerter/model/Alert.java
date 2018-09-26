package com.satownsend.stargazingalerts.alerter.model;

import com.satownsend.stargazingalerts.user.model.User;

import javax.persistence.*;

@Entity
public class Alert {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alertMessage")
    private String alertMessage;

    @ManyToOne
    private User user;

    public Alert() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
