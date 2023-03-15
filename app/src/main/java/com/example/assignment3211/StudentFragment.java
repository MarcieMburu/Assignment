package com.example.assignment3211;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.assignment3211.databinding.FragmentStudentBinding;

import org.checkerframework.checker.units.qual.A;

public class StudentFragment extends Fragment {
    // to use with viewBinding
    private FragmentStudentBinding binding;
    String firstName, middleName, lastName, idNo, regNo, gender, course, department, school;
    ArrayAdapter<String> coursesAdapter, departmentsAdapter, schoolAdapter;

    Button btnSubmit, btnCancel;
    private String[] courses = {"Computer Science", "IT", "BBIT"};
    private String[] departments = {"Computer Science", "IT ", "BBIT"};
    private String[] schools = {"Computer Science & IT", "School of Engineering ", "School of Business"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // initialize spinner adapters
        coursesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, courses);
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, departments);
        departmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, schools);
        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // set spinner adapters
        binding.spCourses.setAdapter(coursesAdapter);
        binding.spDepartment.setAdapter(departmentsAdapter);
        binding.spSchool.setAdapter(schoolAdapter);




        firstName = binding.edtFirstname.getText().toString();
        middleName = binding.edtMiddlename.getText().toString();
        lastName = binding.edtLastname.getText().toString();
        idNo = binding.edtIdno.getText().toString();
        regNo = binding.edtRegno.getText().toString();



        //spinner click listeners (Select listeners)
        binding.spCourses.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));
        binding.spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spSchool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        // Inflate the layout for this fragment
        return view;


    }
}