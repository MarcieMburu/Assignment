package com.example.assignment3211.models;

import java.util.HashMap;
import java.util.Map;

public class User {
    // user properties
    private String firstName, middleName, lastName, idNo, regNo, gender, course, department, school;

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Map<String, String> toMap(){
        Map<String, String> data = new HashMap<>();
        data.put("firstName", firstName);
        data.put("middleName", middleName);
        data.put("lastName", lastName);
        data.put("idNo", idNo);
        data.put("regNo", regNo);
        data.put("gender", gender);
        data.put("course", course);
        data.put("department", department);
        data.put("school", school);
        return data;
    }
}
