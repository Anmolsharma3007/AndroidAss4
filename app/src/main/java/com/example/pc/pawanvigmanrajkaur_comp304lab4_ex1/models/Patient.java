package com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models;

public class Patient {
    int patientId;
    String firstname;
    String lastname;
    String department;
    int doctorId;
    String room;

    public Patient(){}

    public Patient(int patientId, String firstname, String lastname, String department, int doctorId, String room) {
        this.patientId = patientId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.doctorId = doctorId;
        this.room = room;
    }

    public Patient(String firstname, String lastname, String department, int doctorId, String room) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.doctorId = doctorId;
        this.room = room;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Patient#"+ patientId + ": " + firstname + " " + lastname;
    }
}
