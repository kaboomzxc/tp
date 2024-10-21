package bookbob.entity;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String name;
    private String nric;
    private String dateOfBirth;
    private String phoneNumber;
    private String homeAddress;
    private String diagnosis;
    private List<String> medication;
    private String allergy;
    private String sex;
    private String medicalHistory;


    // default constructor only takes in name and NRIC
    public Patient(String name, String nric) {
        this.name = name;
        this.nric = nric;
        this.dateOfBirth = "";
        this.phoneNumber = "";
        this.homeAddress = "";
        this.diagnosis = "";
        this.medication = new ArrayList<>();
        this.allergy = "";
        this.sex = "";
        this.medicalHistory = "";
    }

    // constructor used in retrieving data
    public Patient(String name, String nric, String phoneNumber, String dateOfBirth, String homeAddress,
                   String diagnosis, List<String> medications, String allergy, String sex, String medicalHistory) {
        this.name = name;
        this.nric = nric;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.diagnosis = diagnosis;
        this.medication = medications;
        this.allergy = allergy;
        this.sex = sex;
        this.medicalHistory = medicalHistory;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<String> getMedication() {
        return medication;
    }

    public void setMedication(List<String> medication) {
        this.medication = medication;
    }
    // @@author kaboomzxc
    public String getAllergy() {
        return allergy;
    }
    // @@author kaboomzxc
    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }
    // @@author kaboomzxc
    public String getSex() {
        return sex;
    }
    // @@author kaboomzxc
    public void setSex(String sex) {
        this.sex = sex;
    }
    // @@author kaboomzxc
    public String getMedicalHistory() {
        return medicalHistory;
    }
    // @@author kaboomzxc
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }


    @Override
    public String toString() {
        return "Name: " + getName() + ", NRIC: " + getNric() +
                ", Phone: " + getPhoneNumber() + ", Diagnosis: " + getDiagnosis() +
                ", Medication: " + getMedication() + ", Address: " + getHomeAddress() +
                ", DOB: " + getDateOfBirth() + ", Allergy: " + getAllergy() +
                ", Sex: " + getSex() + ", Medical History: " + getMedicalHistory();
    }

}
