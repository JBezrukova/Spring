package com.example.med.controllers;


import com.example.med.entities.CardNote;
import com.example.med.entities.User;
import com.example.med.entities.UserCard;
import com.example.med.repositories.CardNotesRepository;
import com.example.med.repositories.UserCardRepository;
import com.example.med.repositories.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserCardController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCardRepository userCardRepository;

    @Autowired
    CardNotesRepository cardNotesRepository;


    @RequestMapping(value = "/card",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "Accept=*/*")
    public Object getUserCard(@RequestBody String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data.replaceAll("\n", ""));
        User user = userRepository.findUserByUserId(jsonObject.getInt("userId"));
        UserCard userCard = userCardRepository.findUserCardByUser(user);
        return userCard;
    }

    @RequestMapping(value = "/card_notes ",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "Accept=*/*")
    public Object getUserCardNotes(@RequestBody String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data.replaceAll("\n", ""));
        User user = userRepository.findUserByUserId(jsonObject.getInt("userId"));
        UserCard userCard = userCardRepository.findUserCardByUser(user);
        List<CardNote> notes = cardNotesRepository.findCardNotesByUserCard(userCard);
        return notes;
    }


}
