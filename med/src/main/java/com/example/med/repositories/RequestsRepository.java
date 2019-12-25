package com.example.med.repositories;

import com.example.med.entities.Doctor;
import com.example.med.entities.Request;
import com.example.med.entities.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RequestsRepository extends CrudRepository<Request, Integer> {

    List<Request> findAllByDoctor(Doctor doctor);

    List<Request> findAllByUserEquals(User user);

    Request findRequestById(int id);
}
