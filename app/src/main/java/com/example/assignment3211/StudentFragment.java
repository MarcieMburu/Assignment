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
import android.widget.Toast;

import com.example.assignment3211.databinding.FragmentStudentBinding;
import com.example.assignment3211.models.Student;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentFragment extends Fragment {
    // to use with viewBinding
    private FragmentStudentBinding binding;
    String firstName, middleName, lastName, idNo, regNo, gender, course="", department="", school="";
    ArrayAdapter<String> coursesAdapter, departmentsAdapter, schoolAdapter;
    private String[] courses = {"Computer Science", "IT", "BBIT"};
    private String[] departments = {"Computer Science", "IT ", "BBIT"};
    private String[] schools = {"Computer Science & IT", "School of Engineering ", "School of Business"};
    FirebaseFirestore db;
    ProgressDialog loader;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        binding.spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               department = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spSchool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                school = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Inflate the layout for this fragment
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
        binding.btnSubmit.setOnClickListener(v -> {
            // get user data
            firstName = binding.edtFirstname.getText().toString();
            middleName = binding.edtMiddlename.getText().toString();
            lastName = binding.edtLastname.getText().toString();
            idNo = binding.edtIdno.getText().toString();
            regNo = binding.edtRegno.getText().toString();


            // check if all EditText fields are filled
            if (firstName.isEmpty() || lastName.isEmpty() || idNo.isEmpty() || regNo.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // get gender
            if (binding.rbMale.isChecked()){
                gender = binding.rbMale.getText().toString();
            }else {
                gender = binding.rbFemale.getText().toString();
            }

            // initialize user data
            Student student = new Student();
            student.setFirstName(firstName);
            student.setMiddleName(middleName);
            student.setLastName(lastName);
            student.setGender(gender);
            student.setIdNo(idNo);
            student.setRegNo(regNo);
            student.setSchool(school);
            student.setDepartment(department);
            student.setCourse(course);

            // save data to firebase
            loader.setMessage("Saving data...");
            loader.show();
            //db.collection("Students").add(user.toMap()).addOnCompleteListener(task -> { // auto-generated document Id
            db.collection("Students").document("1").set(student.toMap()).addOnCompleteListener(task -> {
                loader.dismiss();
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(), "Unable to save student data: "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getActivity(), "Student data saved successfully", Toast.LENGTH_SHORT).show();
            });
        });



        // set click listener for cancel button
        binding.btnCancel.setOnClickListener(v -> {
            // clear input fields
            binding.edtFirstname.setText("");
            binding.edtMiddlename.setText("");
            binding.edtLastname.setText("");
            binding.edtIdno.setText("");
            binding.edtRegno.setText("");
            binding.rbMale.setChecked(true);
            binding.spCourses.setSelection(0);
            binding.spDepartment.setSelection(0);
            binding.spSchool.setSelection(0);
        });

    }
}


