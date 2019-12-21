package com.example.med.repositories;

import com.example.med.entities.CardNote;
import com.example.med.entities.UserCard;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CardNotesRepository extends CrudRepository<CardNote, Integer> {

    List<CardNote> findCardNotesByUserCard(UserCard userCard);
}
