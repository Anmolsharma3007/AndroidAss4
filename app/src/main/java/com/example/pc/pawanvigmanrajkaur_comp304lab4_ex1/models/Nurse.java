package com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models;

public class Nurse {
    int nurseId;
    String firstname;
    String lastname;
    String department;
    String password;

    public Nurse(){}

    public Nurse(int nurseId, String firstname, String lastname, String department) {
        this.nurseId = nurseId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.password = password;
    }

    public Nurse(int nurseId, String firstname, String lastname, String department, String password) {
        this.nurseId = nurseId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.password = password;
    }

    public Nurse(String firstname, String lastname, String department, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.password = password;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Nurse#" + nurseId + ": " + firstname + " " +lastname;
    }
}

