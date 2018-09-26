package com.satownsend.stargazingalerts.alerter.dao;

import com.satownsend.stargazingalerts.alerter.model.Alert;
import com.satownsend.stargazingalerts.alerter.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertDao {

    @Autowired
    private AlertRepository repo;

    public Alert findById(Long id){

        return repo.findById(id).get();
    }
}

