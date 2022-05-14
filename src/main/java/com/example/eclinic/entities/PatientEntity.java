package com.example.eclinic.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "patient", schema = "eclinic", catalog = "")
public class PatientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "gender")
    private String gender;
    @Basic
    @Column(name = "weight")
    private Integer weight;
    @Basic
    @Column(name = "hieght")
    private Integer hieght;
    @Basic
    @Column(name = "diagnosis")
    private String diagnosis;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "insuranceCompanyId")
    private Integer insuranceCompanyId;
    @Basic
    @Column(name = "doctorId")
    private Integer doctorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHieght() {
        return hieght;
    }

    public void setHieght(Integer hieght) {
        this.hieght = hieght;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setInsuranceCompanyId(Integer insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientEntity that = (PatientEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(gender, that.gender) && Objects.equals(weight, that.weight) && Objects.equals(hieght, that.hieght) && Objects.equals(diagnosis, that.diagnosis) && Objects.equals(phone, that.phone) && Objects.equals(insuranceCompanyId, that.insuranceCompanyId) && Objects.equals(doctorId, that.doctorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, weight, hieght, diagnosis, phone, insuranceCompanyId, doctorId);
    }
}
