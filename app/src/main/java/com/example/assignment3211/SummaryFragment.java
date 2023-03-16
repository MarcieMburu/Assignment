package com.example.assignment3211;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.assignment3211.databinding.FragmentSummaryBinding;
import com.example.assignment3211.models.Student;
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

        // get all students saved in the database
        db.collection("users").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()){
                Toast.makeText(getActivity(), "Error getting student data: "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                return;
            }

            // data fetched successfully from FireStore
            Toast.makeText(getActivity(), "Data fetched successfully", Toast.LENGTH_SHORT).show();

            QuerySnapshot querySnapshot = task.getResult();
            for (DocumentSnapshot snapshot : querySnapshot.getDocuments()){
                Student user = snapshot.toObject(Student.class);
                // render user interface with user data
                renderUI(user);
            }
        });

    }


    // helper method to render user data to the UI
    private void renderUI(Student user){
        binding.tvName.setText(user.getFirstName()+" "+user.getMiddleName()+" "+user.getLastName());
        binding.tvRegno.setText(user.getRegNo());
        binding.tvIdNo.setText(user.getIdNo());
        binding.tvDepartment.setText(user.getDepartment());
        binding.tvCourse.setText(user.getCourse());
        // TODO render student course details

    }
}