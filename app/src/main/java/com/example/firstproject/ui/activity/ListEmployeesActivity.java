package com.example.firstproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstproject.R;
import com.example.firstproject.model.dao.EmployeeDao;
import com.example.firstproject.model.entity.Employee;
import com.example.firstproject.ui.recycler.adapter.ListEmployeAdapter;
import com.example.firstproject.ui.recycler.adapter.listener.OnItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
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
            public void onItemClick(Employee employee, int position) {
                Intent startDetailsEmployeeActivityWithEmployeeData = new Intent(ListEmployeesActivity.this,
                        DetailsEmployeeActivity.class);
                startDetailsEmployeeActivityWithEmployeeData.putExtra("employee", employee);
                startDetailsEmployeeActivityWithEmployeeData.putExtra("position", position);
                startActivityForResult(startDetailsEmployeeActivityWithEmployeeData, 2);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == 2 && data.hasExtra("employee")){
            Employee employeeData = (Employee) data.getSerializableExtra("employee");
            new EmployeeDao().addEmployee(employeeData);
            adapter.setEmployeeList(employeeData);
        }

        if(requestCode == 2 && resultCode == 2 && data.hasExtra("employee") && data.hasExtra("position")){
            Employee employeeRec = (Employee) data.getSerializableExtra("employee");
            int positionRec = data.getIntExtra("position", -1);
            new EmployeeDao().editEmployee(positionRec, employeeRec);
            adapter.edit(positionRec, employeeRec);
            Toast.makeText(this, ""+employeeRec.getName(), Toast.LENGTH_SHORT).show();

        }

    }
}
