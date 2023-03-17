package com.example.assignment3211;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.assignment3211.databinding.FragmentCourseBinding;
import com.example.assignment3211.models.Course;
import com.google.firebase.firestore.FirebaseFirestore;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;


public class CourseFragment extends Fragment {

    private SharedViewModel viewModel;


        private FragmentCourseBinding binding;

    String course = "", year = "", semester = "", unit = "";
    ArrayAdapter<String> coursesAdapter, yearAdapter, semesterAdapter, unitsAdapter;

    Button btnSave, btnNext;

    private String[] courses = {"Computer Science", "IT", "BBIT"};
    private String[] years = {"First Year ", "Second Year ", "Third Year", "Forth Year"};
    private String[] semesters = {"1st Semester", "2nd Semester  "};
    private String[] units = {"Computer Architecture ", "", ""};


    //firebase
    FirebaseFirestore db;
    //
    ProgressDialog loader;

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
                course = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));

        //spinner click listeners (Select listeners)
        binding.spSemester.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semester = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));
        //spinner click listeners (Select listeners)
        binding.spUnits.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));
        //spinner click listeners (Select listeners)
        binding.spYear.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));


        return view;
    }

    /**
     * Called after the onCreateView is complete
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */







    /////
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();
        loader = new ProgressDialog(getActivity());


        // set click listeners here
        binding.btnSave.setOnClickListener(v -> {
            // initialize user data
            Course user = new Course();
            user.setUnitCode(course);
            user.setUnitName(course);


            // save data to firebase
            loader.setMessage("Saving data...");
            loader.show();
            db.collection("users").add(user.toMap()).addOnCompleteListener(task -> {
                loader.dismiss();
                if (!task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Unable to save student data: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getActivity(), "Student data saved successfully", Toast.LENGTH_SHORT).show();
            });
        });



        // btnNext = view.findViewById(R.id.btnNext); // replace R.id.btnNext with the actual ID of your Next button
        //
        //
        //        btnNext.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                // replace R.id.action_courseFragment_to_nextFragment with the actual ID of your action
        //                NavHostFragment.findNavController(CourseFragment.this).navigate(R.id.nav_graph);
        //            }
        //        });
        //    }}

    }}


