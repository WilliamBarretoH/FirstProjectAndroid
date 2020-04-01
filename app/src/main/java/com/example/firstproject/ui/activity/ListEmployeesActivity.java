package com.example.firstproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstproject.R;
import com.example.firstproject.model.dao.EmployeeDao;
import com.example.firstproject.model.entity.Employee;
import com.example.firstproject.ui.recycler.adapter.ListEmployeAdapter;
import com.example.firstproject.ui.recycler.adapter.listener.OnItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListEmployeesActivity extends AppCompatActivity {

    private List<Employee> employees;
    private ListEmployeAdapter adapter;
    private EmployeeDao employeeDao;
    private List<Employee> allEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employees);
        setUpButtonNewEmployee();
        allEmployees = employeeExample();
        setUpRecyclerView(allEmployees);

    }

    @Override
    protected void onResume() {
        EmployeeDao dao = new EmployeeDao();
        allEmployees.clear();
        allEmployees.addAll(dao.listEmployees());
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    private List<Employee> employeeExample() {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.addEmployee(new Employee("William","20","1500"));

        return employeeDao.listEmployees();
    }

    private void setUpRecyclerView(List<Employee> allEmployees) {
        RecyclerView recyclerEmployees = findViewById(R.id.list_employees_recyclerview);

        setUpAdapter(allEmployees, recyclerEmployees);
        setUpLayoutManager(recyclerEmployees);
    }

    private void setUpLayoutManager(RecyclerView recyclerEmployees) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerEmployees.setLayoutManager(layoutManager);
    }

    private void setUpAdapter(List<Employee> allEmployees, RecyclerView recyclerEmployees) {
        adapter = new ListEmployeAdapter(this, allEmployees);
        recyclerEmployees.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick() {
                Intent goDetailsEmployee = new Intent(ListEmployeesActivity.this,
                        DetailsEmployeeActivity.class);
                startActivity(goDetailsEmployee);
            }
        });
    }

    private void setUpButtonNewEmployee() {
        FloatingActionButton newEmployeeButton = findViewById(R.id.fab_add_new_employee);
        newEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startFormEmployeeActivity = new Intent(ListEmployeesActivity.this,
                        FormEmployeeActivity.class);
                startActivityForResult(startFormEmployeeActivity, 1);
            }
        });
    }



}
