package com.example.med.services;

import com.example.med.entities.User;
import com.example.med.repositories.RoleRepository;
import com.example.med.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }
}
