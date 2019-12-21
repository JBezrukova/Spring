package com.example.med.repositories;

import com.example.med.entities.Administrator;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface AdministratorRepository extends CrudRepository<Administrator, Integer> {

    Administrator findAdministratorByLogin(String login);
}
