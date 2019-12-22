package com.example.med.repositories;

import com.example.med.entities.Doctor;
import com.example.med.entities.Record;
import com.example.med.entities.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RecordsRepository extends CrudRepository<Record, Integer> {

    List<Record> findAllByUserEquals(User user);

    List<Record> findAllByDoctorEquals(Doctor doctor);

    List<Record> findAllByDoctorEqualsAndDateEquals(Doctor doctor, String date);

    Record findRecordByUserAndDoctorAndDateAndTime(User user,
                                                   Doctor doctor,
                                                   String date,
                                                   String time);

    Record findRecordById(int id);

}
