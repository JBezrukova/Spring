package com.example.med.controllers;

import com.example.med.entities.Doctor;
import com.example.med.entities.Record;
import com.example.med.repositories.DoctorRepository;
import com.example.med.repositories.RecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DoctorRecordsController {

    @Autowired
    RecordsRepository recordsRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/doc_logged_records")
    public List<Record> getRecordsForDoctor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Doctor doctor = doctorRepository.findDoctorByLogin(auth.getName());
        List<Record> records = recordsRepository.findAllByDoctorEquals(doctor);
        return records;
    }

    @GetMapping("/doc_records")
    public List<Record> getRecordsForDoctor(@RequestBody String login) {
        login = login.split("=")[1];
        Doctor doctor = doctorRepository.findDoctorByLogin(login);
        List<Record> records = recordsRepository.findAllByDoctorEquals(doctor);
        return records;
    }

}
