package com.example.pc.pawanvigmanrajkaur_comp304lab4_ex1.models;

public class Test {
    int testId;
    int patientId;
    int nurseId;
    String bpl;
    String bph;
    String temperature;

    public Test(){}

    public Test(int testId, int patientId, int nurseId, String bpl, String bph, String temperature) {
        this.testId = testId;
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.bpl = bpl;
        this.bph = bph;
        this.temperature = temperature;
    }

    public Test(int patientId, int nurseId, String bpl, String bph, String temperature) {
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.bpl = bpl;
        this.bph = bph;
        this.temperature = temperature;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public String getBpl() {
        return bpl;
    }

    public void setBpl(String bpl) {
        this.bpl = bpl;
    }

    public String getBph() {
        return bph;
    }

    public void setBph(String bph) {
        this.bph = bph;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Test#" + testId +
                ": patientId=" + patientId +
                ", nurseId=" + nurseId ;
    }
}

