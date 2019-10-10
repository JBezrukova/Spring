package com.example.med.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_card")
public class UserCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_card_id")
    private int ID;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "passport", length = 10)
    private String passport;

    @Column(name = "oms", length = 20)
    private String oms;

    @Column(name = "chronic_desease", length = 200)
    private String chronicDesease;

    @Column(name = "notes", length = 100)
    private String notes;

    @OneToMany(mappedBy = "userCard")
    private Set<CardNote> noteSet = new HashSet<>();

    public Set<CardNote> getNoteSet() {
        return noteSet;
    }

    public void setNoteSet(Set<CardNote> noteSet) {
        this.noteSet = noteSet;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getOms() {
        return oms;
    }

    public void setOms(String oms) {
        this.oms = oms;
    }

    public String getChronicDesease() {
        return chronicDesease;
    }

    public void setChronicDesease(String chronicDesease) {
        this.chronicDesease = chronicDesease;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
