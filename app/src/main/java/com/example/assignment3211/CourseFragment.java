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

import com.example.assignment3211.databinding.FragmentCourseBinding;
import com.example.assignment3211.databinding.FragmentStudentBinding;


public class CourseFragment extends Fragment {

    private FragmentCourseBinding binding;

    String course, year, semester, unit;
    ArrayAdapter<String> coursesAdapter, yearAdapter, semesterAdapter, unitsAdapter;

    Button btnSubmit, btnCancel;
    private String[] courses = {"Computer Science", "IT", "BBIT"};
    private String[] years = {"First Year ", "Second Year ", "Third Year", "Forth Year"};
    private String[] semesters = {"1st Semester", "2nd Semester  "};
    private String[] units = {"Computer Science & IT", "School of Engineering ", "School of Business"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentCourseBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // initialize spinner adapters
        coursesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, courses);
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, semesters);
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, units);
        unitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // set spinner adapters
        binding.spCourses.setAdapter(coursesAdapter);
        binding.spYear.setAdapter(yearAdapter);
        binding.spSemester.setAdapter(semesterAdapter);
        binding.spUnits.setAdapter(unitsAdapter);


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
        binding.spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spUnits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
