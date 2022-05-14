package com.example.eclinic.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "insurance", schema = "eclinic", catalog = "")
public class InsuranceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "accreditationNumber")
    private String accreditationNumber;
    @Basic
    @Column(name = "sessionPrice")
    private double sessionPrice;
    @Basic
    @Column(name = "fax")
    private String fax;
    @Basic
    @Column(name = "keyPersonPhone")
    private String keyPersonPhone;
    @Basic
    @Column(name = "keyPersonName")
    private String keyPersonName;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccreditationNumber() {
        return accreditationNumber;
    }

    public void setAccreditationNumber(String accreditationNumber) {
        this.accreditationNumber = accreditationNumber;
    }

    public double getSessionPrice() {
        return sessionPrice;
    }

    public void setSessionPrice(double sessionPrice) {
        this.sessionPrice = sessionPrice;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getKeyPersonPhone() {
        return keyPersonPhone;
    }

    public void setKeyPersonPhone(String keyPersonPhone) {
        this.keyPersonPhone = keyPersonPhone;
    }

    public String getKeyPersonName() {
        return keyPersonName;
    }

    public void setKeyPersonName(String keyPersonName) {
        this.keyPersonName = keyPersonName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceEntity that = (InsuranceEntity) o;
        return id == that.id && Double.compare(that.sessionPrice, sessionPrice) == 0 && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && Objects.equals(accreditationNumber, that.accreditationNumber) && Objects.equals(fax, that.fax) && Objects.equals(keyPersonPhone, that.keyPersonPhone) && Objects.equals(keyPersonName, that.keyPersonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, accreditationNumber, sessionPrice, fax, keyPersonPhone, keyPersonName);
    }
}
