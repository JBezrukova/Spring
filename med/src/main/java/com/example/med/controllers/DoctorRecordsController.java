package com.example.med.controllers;

import com.example.med.entities.Doctor;
import com.example.med.entities.DoctorCategory;
import com.example.med.entities.Record;
import com.example.med.repositories.CategoryRepository;
import com.example.med.repositories.DoctorRepository;
import com.example.med.repositories.RecordsRepository;
import com.example.med.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorRecordsController {

    @Autowired
    RecordsRepository recordsRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/doc_records_free_time")
    public List<String> getFreeTimeForDoctor(@RequestBody String body) {
        String login = body.split("=")[1].split("&")[0];
        String day = body.split("&")[1].split("=")[1];
        Doctor doctor = doctorRepository.findDoctorByLogin(login);
        List<Record> records = recordsRepository.findAllByDoctorEqualsAndDateEquals(doctor, day);
        List<String> times = TimeService.createScheduleForDoctor(records);
        return times;
    }


    @GetMapping("/doc_records")
    public List<Record> getRecordsForDoctor(@RequestBody String login) {
        login = login.split("=")[1];
        Doctor doctor = doctorRepository.findDoctorByLogin(login);
        List<Record> records = recordsRepository.findAllByDoctorEquals(doctor);
        return records;
    }

    @GetMapping("/docs_with_specialisation")
    public List<Doctor> getDoctorsWithSpecialisation(@RequestBody String i) {
        i = i.split("=")[1];
        DoctorCategory doctorCategory = categoryRepository
                .findByCategoryId(Integer.valueOf(i));
        List<Doctor> doctors = doctorRepository.findBySpecialization(doctorCategory);
        return doctors;
    }

}
