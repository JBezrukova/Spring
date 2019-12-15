package com.example.med.services;

import com.example.med.entities.Doctor;
import com.example.med.entities.Record;
import com.example.med.repositories.DoctorRepository;
import com.example.med.repositories.RecordsRepository;
import org.hibernate.loader.plan.exec.process.spi.ReaderCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeService {

    private static List<String> generateTime() {
        List<String> time = new ArrayList<>();
        for (int i = 8; i < 17; i++) {
            time.add(i + ":00");
        }
        return time;
    }

    public static List<String> createScheduleForDoctor(List<Record> records) {
        List<String> time = generateTime();
        for (Record record : records) {
            time.remove(record.getTime());
        }
        return time;
    }
}
