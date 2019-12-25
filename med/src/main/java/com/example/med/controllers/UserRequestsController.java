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
import java.util.stream.Collectors;

@RestController
public class UserRequestsController {

    @Autowired
    RequestsRepository requestsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    RecordsRepository recordsRepository;

    @RequestMapping(value = "/requests",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "Accept=*/*")
    public List<Request> getAllRecordForUser(@RequestBody String login) {
        List<Request> requests = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(login.replaceAll("/n", ""));
            User user = userRepository.findUserByLogin(jsonObject.getString("login"));
            requests = requestsRepository.findAllByUserEquals(user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requests = requests.stream()
                .filter(request -> !request.isApprovedByAdmin() ||
                        !request.isApprovedByDoctor())
                .collect(Collectors.toList());
        return requests;
    }

    @RequestMapping(value = "/create_request",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "Accept=*/*")
    public Request createRequest(@RequestBody String record) {
        Request request = new Request();
        try {
            JSONObject jsonObject = new JSONObject(record.replaceAll("\n", ""));
            Doctor doctor = getDoctor(jsonObject);
            User user = getUser(jsonObject);
            request = ObjectCreator.createRequest(user, doctor, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestsRepository.save(request);
    }

    private User getUser(JSONObject jsonObject) throws JSONException {
        return userRepository.findUserByUserId(Integer.valueOf(jsonObject.getJSONObject("user").getString("userId")));
    }

    private Doctor getDoctor(JSONObject jsonObject) throws JSONException {
        return doctorRepository.findByDoctorId(Integer.valueOf(jsonObject.getJSONObject("doctor").getString("id")));
    }

    @RequestMapping(value = "/update_request",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "Accept=*/*")
    public Request updateRequest(@RequestBody String record) {
        Request request = null;
        try {
            JSONObject jsonObject = new JSONObject(record.replaceAll("\n", ""));
            String id = jsonObject.getString("id");
            request = requestsRepository.findRequestById(Integer.valueOf(id));
            Doctor doctor = getDoctor(jsonObject);
            User user = getUser(jsonObject);
            request = ObjectCreator.updateRequest(request, user, doctor, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (request.isApprovedByAdmin() && request.isApprovedByDoctor()) {
            if (request.getReason().equals("create")) {
                Record record1 = ObjectCreator.createRecordFromRequest(request);
                recordsRepository.save(record1);
            } else {
                Record record1 = recordsRepository.findRecordByUserAndDoctorAndDateAndTime(
                        request.getUser(),
                        request.getDoctor(),
                        request.getDate(),
                        request.getTime());
                recordsRepository.delete(record1);
            }
        }
        return requestsRepository.save(request);
    }

    @GetMapping("/all_requests")
    public List<Request> getAllRequests() {
        List<Request> requests = (List<Request>) requestsRepository.findAll();
        requests = requests.stream()
                .filter(request -> !request.isApprovedByAdmin())
                .collect(Collectors.toList());
        return requests;
    }
}
