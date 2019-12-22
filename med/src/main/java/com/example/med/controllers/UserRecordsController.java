package com.example.med.controllers;

import com.example.med.entities.Doctor;
import com.example.med.entities.Record;
import com.example.med.entities.Request;
import com.example.med.entities.User;
import com.example.med.repositories.DoctorRepository;
import com.example.med.repositories.RecordsRepository;
import com.example.med.repositories.RequestsRepository;
import com.example.med.repositories.UserRepository;
import com.example.med.services.ObjectCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRecordsController {

    @Autowired
    RecordsRepository recordsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    RequestsRepository requestsRepository;

    @RequestMapping(value = "/records",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "Accept=*/*")
    public List<Record> getAllRecordForUser(@RequestBody String login) {
        List<Record> records = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(login.replaceAll("/n", ""));
            User user = userRepository.findUserByLogin(jsonObject.getString("login"));
            records = recordsRepository.findAllByUserEquals(user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return records;
    }

    @RequestMapping(value = "/get_record",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "Accept=*/*")
    public Record getRecord(@RequestBody String id) {
        Record record1 = new Record();
        try {
            JSONObject jsonObject = new JSONObject(id.replaceAll("\n", ""));
            int recordId = jsonObject.getInt("id");
            record1 = recordsRepository.findRecordById(recordId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return record1;
    }


    @RequestMapping(value = "/remove_record",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "Accept=*/*")
    public Request removeRecord(@RequestBody String id) {
        Request request = new Request();
        try {
            JSONObject jsonObject = new JSONObject(id.replaceAll("\n", ""));
            int recordId = jsonObject.getInt("id");
            Record record1 = recordsRepository.findRecordById(recordId);
            request = ObjectCreator.createRequestForDelete(record1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestsRepository.save(request);
    }

    @RequestMapping(value = "/create_record",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "Accept=*/*")
    public Record createRecord(@RequestBody String record) {
        Record record1 = new Record();
        try {
            JSONObject jsonObject = new JSONObject(record.replaceAll("\n", ""));
            Doctor doctor = doctorRepository.findByDoctorId(Integer.valueOf(jsonObject.getJSONObject("doctor").getString("id")));
            User user = userRepository.findUserByUserId(Integer.valueOf(jsonObject.getJSONObject("user").getString("user_id")));
            record1 = ObjectCreator.createRecord(user, doctor, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recordsRepository.save(record1);
    }
}
