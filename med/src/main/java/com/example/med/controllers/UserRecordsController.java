package com.example.med.controllers;

import com.example.med.entities.Record;
import com.example.med.entities.User;
import com.example.med.repositories.RecordsRepository;
import com.example.med.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRecordsController {

    @Autowired
    RecordsRepository recordsRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/records")
    public List<Record> geeAllRecordForUser(@RequestBody String login) {
        String userLogin = login.split("=")[1];
        User user = userRepository.findUserByLogin(userLogin);
        List<Record> records = recordsRepository.findAllByUserEquals(user);
        return records;
    }

//    @PostMapping("/records")
//    public Record createRequest(@RequestParam int recordId) {
//
//        return recordsRepository.save(record);
//    }
}
