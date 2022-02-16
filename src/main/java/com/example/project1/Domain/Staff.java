package com.example.project1.Domain;

import javax.persistence.*;

@Entity(name = "Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String secondName;
    private String password;
    private String email;
    private String phoneNumber;
    private String roleID; // ForeignKey

    public Staff() {
    }

    public Staff(String name, String surname, String password, String email, String phoneNumber, String roleID) {
        this.firstName = name;
        this.secondName = surname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roleID = roleID;
    }

    public Staff( String name, String surname, String password, String email) {
        this.firstName = name;
        this.secondName = surname;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String surname) {
        this.secondName = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
}
