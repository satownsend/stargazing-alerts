package com.satownsend.stargazingalerts.alerter.repository;

import com.satownsend.stargazingalerts.alerter.model.Alert;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AlertRepository extends CrudRepository<Alert, Long> {
    Optional<Alert> findById(Long id);
}
