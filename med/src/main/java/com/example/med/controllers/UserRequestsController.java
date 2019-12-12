package com.example.med.controllers;

import com.example.med.entities.Request;
import com.example.med.entities.User;
import com.example.med.repositories.RequestsRepository;
import com.example.med.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserRequestsController {

    @Autowired
    RequestsRepository requestsRepository;

    @Autowired
    UserRepository userRepository;

    //получиь заявки
    @GetMapping("/requests")
    public List<Request> getAllRequestForUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByLogin(auth.getName());
        List<Request> requests = requestsRepository.findAllByUserEquals(user);
        return requests;
    }

    @PostMapping("/requests")
    public Request createRequest(@RequestBody Request request) {
        return requestsRepository.save(request);
    }
}
