package com.example.med.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctorId")
    private Integer doctorId;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "birth_date")
    private String birthDay;

    @Column(name = "phone")
    private String phone;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private DoctorCategory specialization;

    @OneToMany(mappedBy = "doctor")
    @JsonBackReference
    private Set<Record> records = new HashSet<>();

    @OneToMany(mappedBy = "doctor")
    @JsonBackReference
    private Set<Request> requests = new HashSet<>();

    @OneToMany(mappedBy = "doctor")
    @JsonBackReference
    private Set<CardNote> cardNotes = new HashSet<>();

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    public Integer getID() {
        return doctorId;
    }

    public void setID(Integer ID) {
        this.doctorId = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DoctorCategory getSpecialization() {
        return specialization;
    }

    public void setSpecialization(DoctorCategory specialization) {
        this.specialization = specialization;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<CardNote> getCardNotes() {
        return cardNotes;
    }

    public void setCardNotes(Set<CardNote> cardNotes) {
        this.cardNotes = cardNotes;
    }
}
