package com.example.assignment3211;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;




public class SharedViewModel extends ViewModel {

    private String course;

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }
}





