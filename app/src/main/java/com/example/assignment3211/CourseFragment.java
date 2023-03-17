package com.example.assignment3211;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.assignment3211.databinding.FragmentCourseBinding;
import com.example.assignment3211.models.CourseDetails;
import com.example.assignment3211.models.Unit;
import com.google.firebase.firestore.FirebaseFirestore;


public class CourseFragment extends Fragment {

    private FragmentCourseBinding binding;
  
    String course = "", year = "", semester = "";
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

        // set spinner adapters
        binding.spCourses.setAdapter(coursesAdapter);
        binding.spYear.setAdapter(yearAdapter);
        binding.spSemester.setAdapter(semesterAdapter);

        //spinner click listeners (Select listeners)
        binding.spCourses.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		    // set course
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
		    // set semester
                semester = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));
        //spinner click listeners (Select listeners)
        binding.spYear.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // set year
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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();
        loader = new ProgressDialog(getActivity());

        // set click listeners here
        binding.btnSave.setOnClickListener(v -> {
            // initialize CourseDetails data
            CourseDetails courseDetails =  new CourseDetails();
            courseDetails.setCourse(course);
            courseDetails.setYear(year);
            courseDetails.setSemester(semester);

            // save data to firebase
            loader.setMessage("Saving Course data...");
            loader.show();
            db.collection("Students").document("1").collection("CourseDetails").document("1").set(courseDetails.toMap()).addOnCompleteListener(task -> {
                loader.dismiss();
                if (!task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Unable to save Course data: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getActivity(), "Course data saved successfully", Toast.LENGTH_SHORT).show();
            });
        });
    }}
