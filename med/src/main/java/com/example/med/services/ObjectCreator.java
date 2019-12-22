package com.example.med.services;

import com.example.med.entities.Doctor;
import com.example.med.entities.Record;
import com.example.med.entities.Request;
import com.example.med.entities.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ObjectCreator {

    public static Record createRecord(User user, Doctor doctor, JSONObject jsonObject) throws JSONException {
        Record record = new Record();
        record.setDoctor(doctor);
        record.setUser(user);
        record.setDate(jsonObject.getString("date"));
        record.setTime(jsonObject.getString("time"));
        return record;
    }

    public static Request createRequest(User user, Doctor doctor, JSONObject jsonObject) throws JSONException {
        Request request = new Request();
        request.setUser(user);
        request.setDoctor(doctor);
        request.setDate(jsonObject.getString("date"));
        request.setTime(jsonObject.getString("time"));
        request.setReason(jsonObject.getString("reason"));
        request.setApprovedByAdmin(false);
        request.setApprovedByDoctor(false);
        return request;
    }

    public static Request updateRequest(Request request, User user, Doctor doctor, JSONObject jsonObject) throws JSONException {
        request.setUser(user);
        request.setDoctor(doctor);
        request.setDate(jsonObject.getString("date"));
        request.setTime(jsonObject.getString("time"));
        request.setReason(jsonObject.getString("reason"));
        String byAdmin = jsonObject.getString("approvedByAdmin");
        String byDoctor = jsonObject.getString("approvedByDoctor");
        if (byAdmin.equals("true")) {
            request.setApprovedByAdmin(true);
        } else {
            request.setApprovedByAdmin(false);
        }
        if (byDoctor.equals("true")) {
            request.setApprovedByDoctor(true);
        } else {
            request.setApprovedByDoctor(false);
        }
        return request;
    }

    public static Record createRecordFromRequest(Request request) {
        Record record = new Record();
        record.setUser(request.getUser());
        record.setTime(request.getTime());
        record.setDate(request.getDate());
        record.setDoctor(request.getDoctor());
        return record;
    }

    public static Request createRequestForDelete(Record record){
        Request request = new Request();
        request.setUser(record.getUser());
        request.setDoctor(record.getDoctor());
        request.setApprovedByDoctor(false);
        request.setApprovedByAdmin(false);
        request.setReason("delete");
        request.setTime(record.getTime());
        request.setDate(record.getDate());
        return request;
    }
}
