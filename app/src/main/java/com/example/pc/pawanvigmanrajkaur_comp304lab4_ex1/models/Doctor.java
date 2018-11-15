package com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models;

public class Doctor {
    int doctorId;
    String firstname;
    String lastname;
    String department;
    String password;

    public Doctor(int doctorId, String firstname, String lastname, String department, String password) {
        this.doctorId = doctorId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.password = password;
    }

    public Doctor(String firstname, String lastname, String department, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.password = password;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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
}
