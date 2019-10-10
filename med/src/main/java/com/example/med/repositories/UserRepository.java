package com.example.med.repositories;

import com.example.med.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByLogin(String login);
}
