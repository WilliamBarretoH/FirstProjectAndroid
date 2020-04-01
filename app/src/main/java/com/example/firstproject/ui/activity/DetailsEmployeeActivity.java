package com.example.firstproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.firstproject.R;

public class DetailsEmployeeActivity extends AppCompatActivity {

    private TextView fieldName;
    private TextView fieldAge;
    private TextView fieldSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_employee);



    }
}
