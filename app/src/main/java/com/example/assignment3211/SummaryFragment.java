package com.example.assignment3211;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment3211.databinding.FragmentSummaryBinding;
import com.example.assignment3211.models.CourseDetails;
import com.example.assignment3211.models.Student;
import com.example.assignment3211.models.Unit;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class SummaryFragment extends Fragment {
    private FragmentSummaryBinding binding;
    FirebaseFirestore db;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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

    // get data from Firebase (Student, CourseDetails, Units)
    private void getData(){
        DocumentReference studentDocRef =  db.collection("Students").document("1");
        DocumentReference courseDetailsDocRef = studentDocRef.collection("CourseDetails").document("1");
        CollectionReference unitsDocRef = db.collection("Units");
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
        // get saved units
        unitsDocRef.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()){
                Toast.makeText(getActivity(), "Error getting Units: "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
           QuerySnapshot snapshot = task.getResult();
            ArrayList<Unit> units = new ArrayList<>();
           for (DocumentSnapshot document : snapshot.getDocuments()){
               Unit unit = document.toObject(Unit.class); // convert document into a custom class
               units.add(unit);
           }

           // re-create table with units
            if (units.size() > 0) renderTable(units, binding.tlUnits);

            Toast.makeText(getActivity(), "Units data fetched successfully", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * create units table
     * @param units An array of units used to create table rows
     * @param tableLayout the table layout to add the table rows to
     */
    public void renderTable(ArrayList<Unit> units, ViewGroup tableLayout){
        Context context = requireContext();
        tableLayout.removeAllViews();

        // row header
        TableRow headerRow = new TableRow(context);
        // set width and height to match_parent
        headerRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT
        ));
        // Col1
        TextView col1 = new TextView(context);
        col1.setText("Unit Code");
        col1.setTextColor(Color.BLACK); // black font color
        col1.setTypeface(null, Typeface.BOLD); // bold text
        // Col2
        TextView col2 = new TextView(context);
        col2.setText("Unit Name");
        col2.setTextColor(Color.BLACK); // black font color
        col2.setTypeface(null, Typeface.BOLD);  // bold text

        headerRow.addView(col1);
        headerRow.addView(col2);
        tableLayout.addView(headerRow);

        // loop through all units creating rows an cols
        for (int i=0; i<units.size(); i++){
            TableRow row = new TableRow(context);
            row.setLayoutParams(new TableLayout.LayoutParams( // set width and height to match_parent
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT
            ));

            TextView column1 = new TextView(context); // col1
            column1.setText(units.get(i).getUnitCode());

            TextView column2 = new TextView(context); // col2
            column2.setText(units.get(i).getUnitName());

            row.addView(column1);
            row.addView(column2);
            tableLayout.addView(row);
        }

    }
}