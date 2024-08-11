package se2203b.ipayroll;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.sql.*;

public class Employee extends iPAYROLLUser {

//    private String SIN;
//    private String province;
//    private String city;
//    private String postalCode;
//    private String phoneNumber;
//    private String jobClass;
//    private java.sql.Date DOB;
//    private String maritalStatus;
//    private int skilLCode;
//    private java.sql.Date dateOfHire;
//    private java.sql.Date dateOfLastPromotion;
//    private String name;

//    public Employee(Login login, String SIN, String phoneNumber, String province, String postalCode, String city, String jobClass,
//                    java.sql.Date DOB, java.sql.Date dateOfHire, java.sql.Date dateOfLastPromotion, String maritalStatus, int skilLCode) {
//        super(login);
//        this.SIN = SIN;
//        this.province = province;
//        this.phoneNumber = phoneNumber;
//        this.jobClass = jobClass;
//        this.postalCode = postalCode;
//        this.DOB = DOB;
//        this.city = city;
//        this.dateOfHire = dateOfHire;
//        this.dateOfLastPromotion = dateOfLastPromotion;
//        this.skilLCode = skilLCode;
//        this.maritalStatus = maritalStatus;
//    }

    private StringProperty SIN;
    private StringProperty province;
    private StringProperty city;
    private StringProperty postalCode;
    private StringProperty phoneNumber;
    private StringProperty jobClass;
    private java.sql.Date DOB;
    private StringProperty maritalStatus;
    private IntegerProperty skilLCode;
    private java.sql.Date dateOfHire;
    private java.sql.Date dateOfLastPromotion;
    private StringProperty name;

    public Employee(String ID, String name, String SIN, String phoneNumber, String province, String postalCode, String city, String jobClass,
                    java.sql.Date DOB, java.sql.Date dateOfHire, java.sql.Date dateOfLastPromotion, String maritalStatus, int skillCode) {
        super(ID, name);
        this.SIN = new SimpleStringProperty(SIN);
        this.province = new SimpleStringProperty(province);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.jobClass = new SimpleStringProperty(jobClass);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.city = new SimpleStringProperty(city);
        this.skilLCode = new SimpleIntegerProperty(skillCode);
        this.maritalStatus = new SimpleStringProperty(maritalStatus);
        this.DOB = DOB;
        this.dateOfHire = dateOfHire;
        this.dateOfLastPromotion = dateOfLastPromotion;
    }

    public Employee(String name, String SIN, String phoneNumber, String province, String postalCode, String city, String jobClass,
                    java.sql.Date DOB, java.sql.Date dateOfHire, java.sql.Date dateOfLastPromotion, String maritalStatus, int skillCode) {
//        super();
//        this.SIN = SIN;
//        this.province = province;
//        this.phoneNumber = phoneNumber;
//        this.jobClass = jobClass;
//        this.postalCode = postalCode;
//        this.DOB = DOB;
//        this.city = city;
//        this.dateOfHire = dateOfHire;
//        this.dateOfLastPromotion = dateOfLastPromotion;
//        this.skilLCode = skilLCode;
//        this.maritalStatus = maritalStatus;
        super();
        this.name = new SimpleStringProperty(name);
        this.SIN = new SimpleStringProperty(SIN);
        this.province = new SimpleStringProperty(province);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.jobClass = new SimpleStringProperty(jobClass);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.DOB = DOB;
        this.city = new SimpleStringProperty(city);
        this.dateOfHire = dateOfHire;
        this.dateOfLastPromotion = dateOfLastPromotion;
        this.skilLCode = new SimpleIntegerProperty(skillCode);
        this.maritalStatus = new SimpleStringProperty(maritalStatus);
    }

    public String getSIN() {
        return SIN.get();
    }
    public StringProperty sinProperty() {
        return SIN;
    }
    public void setSIN(String SIN) {
        this.SIN.set(SIN);
    }

    public String getProvince() {
        return province.get();
    }
    public StringProperty provinceProperty() {
        return SIN;
    }
    public void setProvince(String province) {
        this.province.set(province);
    }

    public String getCity() {
        return city.get();
    }
    public StringProperty cityProperty() {
        return SIN;
    }
    public void setCity(String city) {
        this.city.set(city);
    }

    public String getPostalCode() {
        return postalCode.get();
    }
    public StringProperty postalCodeProperty() {
        return SIN;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }
    public StringProperty phoneNumberProperty() {
        return SIN;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getJobClass() {
        return jobClass.get();
    }
    public StringProperty jobClassProperty() {
        return SIN;
    }
    public void setJobClass(String jobClass) {
        this.jobClass.set(jobClass);
    }

    public String getMaritalStatus() {
        return maritalStatus.get();
    }
    public StringProperty maritalStatusProperty() {
        return SIN;
    }
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus.set(maritalStatus);
    }

    public int getSkillCode() {
        return skilLCode.get();
    }
    public IntegerProperty skillCodeProperty() {
        return skilLCode;
    }
    public void setSkillCode(int skillCode) {
        this.skilLCode.set(skillCode);
    }

    public java.sql.Date getDOB() {
        return DOB;
    }
    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public java.sql.Date getDateOfHire() {
        return dateOfHire;
    }
    public void setDateOfHire(Date dateOfHire) {
        this.dateOfHire = dateOfHire;
    }

    public java.sql.Date getDateOfLastPromotion() {
        return dateOfLastPromotion;
    }
    public void setDateOfLastPromotion(Date dateOfLastPromotion) {
        this.dateOfLastPromotion = dateOfLastPromotion;
    }


//    public String getSIN() {
//        return SIN;
//    }
//
//    public void setSIN(String SIN) {
//        this.SIN = SIN;
//    }
//
//    public String getProvince() {
//        return province;
//    }
//
//    public void setProvince(String province) {
//        this.province = province;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getPostalCode() {
//        return postalCode;
//    }
//
//    public void setPostalCode(String postalCode) {
//        this.postalCode = postalCode;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getJobClass() {
//        return jobClass;
//    }
//
//    public void setJobClass(String jobClass) {
//        this.jobClass = jobClass;
//    }
//
//    public java.sql.Date getDOB() {
//        return DOB;
//    }
//
//    public void setDOB(Date DOB) {
//        this.DOB = DOB;
//    }
//
//    public String getMaritalStatus() {
//        return maritalStatus;
//    }
//
//    public void setMaritalStatus(String maritalStatus) {
//        this.maritalStatus = maritalStatus;
//    }
//
//    public int getSkillCode() {
//        return skilLCode;
//    }
//
//    public void setSkillCode(int skillCode) {
//        this.skilLCode = skillCode;
//    }
//
//    public java.sql.Date getDateOfHire() {
//        return dateOfHire;
//    }
//
//    public void setDateOfHire(Date dateOfHire) {
//        this.dateOfHire = dateOfHire;
//    }
//
//    public java.sql.Date getDateOfLastPromotion() {
//        return dateOfLastPromotion;
//    }
//
//    public void setDateOfLastPromotion(Date dateOfLastPromotion) {
//        this.dateOfLastPromotion = dateOfLastPromotion;
//    }
}
