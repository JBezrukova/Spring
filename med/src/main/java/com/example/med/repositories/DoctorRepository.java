package com.example.med.repositories;

import com.example.med.entities.Doctor;
import com.example.med.entities.DoctorCategory;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface DoctorRepository extends CrudRepository<Doctor, Integer> {

    Doctor findDoctorByLogin(String login);

    List<Doctor> findBySpecialization(DoctorCategory category);

    Doctor findByDoctorId(int id);
}
