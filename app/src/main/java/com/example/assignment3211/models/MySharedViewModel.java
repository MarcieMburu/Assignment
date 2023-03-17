
package com.example.assignment3211.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MySharedViewModel extends ViewModel {
	private MutableLiveData<String> data = new MutableLiveData<>();

	public LiveData<String> getData(){
		return  data;
	}

	public void setData(String newData){
		data.setValue(newData);
	}
}
