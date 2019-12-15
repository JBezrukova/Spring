package com.example.med.repositories;

import com.example.med.entities.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByLogin(String login);

    User findUserByUserId(int id);
}
