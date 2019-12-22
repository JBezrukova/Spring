package com.example.med.controllers;

import com.example.med.entities.Doctor;
import com.example.med.entities.DoctorCategory;
import com.example.med.entities.Record;
import com.example.med.repositories.CategoryRepository;
import com.example.med.repositories.DoctorRepository;
import com.example.med.repositories.RecordsRepository;
import com.example.med.services.TimeService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DoctorRecordsController {

    @Autowired
    RecordsRepository recordsRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(value = "/doc_records_free_time",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "Accept=*/*")
    public List<String> getFreeTimeForDoctor(@RequestBody String body) {
        List<String> times = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(body.replaceAll("\n", ""));
            int id = jsonObject.getInt("id");
            String day = jsonObject.getString("day");
            Doctor doctor = doctorRepository.findByDoctorId(id);
            List<Record> records = recordsRepository.findAllByDoctorEqualsAndDateEquals(doctor, day);
            times = TimeService.createScheduleForDoctor(records);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return times;
    }

    @GetMapping("/doc_specialisations")
    public List<DoctorCategory> getAllSpecialisations() {
        return categoryRepository.findAll();
    }

    @RequestMapping(value = "/doc_records",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "Accept=*/*")
    public List<Record> getRecordsForDoctor(@RequestBody String id) {
        Doctor doctor = new Doctor();
        try {
            JSONObject jsonObject = new JSONObject(id.replaceAll("\n", ""));
            doctor = doctorRepository.findByDoctorId(jsonObject.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<Record> records = recordsRepository.findAllByDoctorEquals(doctor);
        return records;
    }

    @RequestMapping(value = "/docs_with_specialisation",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "Accept=*/*")
    public List<Doctor> getDoctorsWithSpecialisation(@RequestBody String id) {
        List<Doctor> doctors = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(id.replaceAll("\n", ""));
            DoctorCategory doctorCategory = categoryRepository
                    .findByCategoryId(Integer.valueOf(jsonObject.getInt("id")));
            doctors = doctorRepository.findBySpecialization(doctorCategory);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return doctors;
    }

}
