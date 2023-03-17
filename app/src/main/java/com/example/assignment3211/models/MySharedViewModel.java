
package com.example.assignment3211.models;

import androidx.lifecycle.ViewModel;

public class MySharedViewModel extends ViewModel {
	private String course;

	// setting data to the model
	public void setCourse(String course) {
		this.course = course;
	}

	// getting course data
	public String getCourse(){
		return this.course;
	}
}
