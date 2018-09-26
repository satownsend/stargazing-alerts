package com.satownsend.stargazingalerts.user.repository;

import com.satownsend.stargazingalerts.user.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findById(Long id);
    List<User> findAll();
}
