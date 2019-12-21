package com.example.med.repositories;

import com.example.med.entities.User;
import com.example.med.entities.UserCard;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserCardRepository extends CrudRepository<UserCard, Integer> {

    UserCard findUserCardByUser(User user);

}
