package com.example.med.controllers;


import com.example.med.BaseError;
import com.example.med.entities.Administrator;
import com.example.med.entities.Doctor;
import com.example.med.entities.User;
import com.example.med.repositories.AdministratorRepository;
import com.example.med.repositories.DoctorRepository;
import com.example.med.repositories.UserRepository;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdministratorRepository administratorRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @RequestMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "Accept=*/*")
    public Object geeAllRecordForUser(@RequestBody String data) throws JSONException {
        try {
            JSONObject jsObject = new JSONObject(data.replaceAll("\n", ""));
            String login = jsObject.getString("login");
            String password = jsObject.getString("password");
            User user = (userRepository.findUserByLogin(login));
            if (user != null &&
                    user.getPassword().equals(password)) {
                return user;
            }
            Administrator administrator = administratorRepository.findAdministratorByLogin(login);
            if (administrator != null &&
                    administrator.getPassword().equals(password)) {
                return administrator;
            }
            Doctor doctor = doctorRepository.findDoctorByLogin(login);
            if (doctor != null &&
                    doctor.getPassword().equals(password)) {
                return doctor;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
