package com.example.med.services;

import com.example.med.entities.User;

public interface UserService {
    User findUserByLogin(String login);
}
