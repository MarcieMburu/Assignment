package com.example.assignment3211;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.assignment3211.databinding.FragmentSummaryBinding;
import com.example.assignment3211.models.CourseDetails;
import com.example.assignment3211.models.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


public class SummaryFragment extends Fragment {
    private FragmentSummaryBinding binding;
    FirebaseFirestore db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSummaryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseFirestore.getInstance();
        // get Student and course details data
        getData();

        // load data on refresh button click
        binding.btnRefresh.setOnClickListener(v -> getData());
    }


    // helper method to render Student info
    private void renderUI(Student student){
        binding.tvName.setText("Full Names: "+student.getFirstName()+" "+student.getMiddleName()+" "+student.getLastName());
        binding.tvRegno.setText("Registration Number: "+student.getRegNo());
        binding.tvIdNo.setText("ID Number: "+student.getIdNo());
        binding.tvGender.setText("Gender: "+student.getGender());
        binding.tvDepartment.setText("Department: "+student.getDepartment());
        binding.tvCourse.setText("Course: "+student.getCourse());
    }

    // helper method to render Student Course Details
    private void renderUI(CourseDetails courseDetails){
        binding.tvCourse.setText("Course: "+courseDetails.getCourse());
        binding.tvYear.setText("Year: "+courseDetails.getYear());
        binding.tvSemester.setText("Semester: "+courseDetails.getSemester());
    }

    // get data from Firebase
    private void getData(){
        DocumentReference studentDocRef =  db.collection("Students").document("1");
        DocumentReference courseDetailsDocRef = studentDocRef.collection("CourseDetails").document("1");
        // get Student data saved in the database
        studentDocRef.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()){
                Toast.makeText(getActivity(), "Error getting student data: "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                return;
            }

            // data fetched successfully from FireStore
            Toast.makeText(getActivity(), "Student data fetched successfully", Toast.LENGTH_SHORT).show();

            DocumentSnapshot snapshot = task.getResult();
            Student user = snapshot.toObject(Student.class);
            // render user interface with user data
            renderUI(user);
        });
        // get Student course details
        courseDetailsDocRef.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()){
                Toast.makeText(getActivity(), "Error getting Course Details: "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(getActivity(), "Course Details data fetched successfully", Toast.LENGTH_SHORT).show();
            DocumentSnapshot snapshot = task.getResult();
            CourseDetails courseDetails = snapshot.toObject(CourseDetails.class);
            renderUI(courseDetails);
        });
    }
}