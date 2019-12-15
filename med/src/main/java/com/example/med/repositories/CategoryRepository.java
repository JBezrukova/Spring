package com.example.med.repositories;

import com.example.med.entities.DoctorCategory;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CategoryRepository extends CrudRepository<DoctorCategory, Integer> {

    List<DoctorCategory> findAll();

    DoctorCategory findByCategoryId(int id);
}
