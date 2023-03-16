package com.example.assignment3211.models;

import java.util.HashMap;
import java.util.Map;

public class Course {
    private String unitCode, unitName;

    public Course() {
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Map<String, String> toMap() {
        Map<String, String> data = new HashMap<>();
        data.put("unitCode", unitCode);
        data.put("unitName", unitName);


        return data;
    }
}

