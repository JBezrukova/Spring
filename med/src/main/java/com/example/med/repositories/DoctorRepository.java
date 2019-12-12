package com.example.med.repositories;

import com.example.med.entities.Doctor;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface DoctorRepository extends CrudRepository<Doctor, Integer> {

    Doctor findDoctorByLogin(String login);

}
