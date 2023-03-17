package com.example.assignment3211;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;



public class MainActivity extends AppCompatActivity {

    ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    RecyclerView recyclerView;


    private String[] titles=new String[]{"Student","Course","Summary"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        viewPager2=findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.tab_layout);

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(this);

        viewPager2.setAdapter(viewPagerFragmentAdapter);

        new TabLayoutMediator(tabLayout, viewPager2,((tab, position) -> tab.setText(titles[position]))).attach();




    }};