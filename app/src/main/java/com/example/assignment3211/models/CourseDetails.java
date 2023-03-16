package com.example.assignment3211.models;

import java.util.HashMap;
import java.util.Map;

public class CourseDetails {
    private String course, year, semester;

    public CourseDetails() {
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Map<String, String> toMap(){
        Map<String, String> data = new HashMap<>();
        data.put("course", course);
        data.put("year", year);
        data.put("semester", semester);
        return data;
    }
}
