package com.example.firstproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstproject.R;
import com.example.firstproject.model.dao.EmployeeDao;
import com.example.firstproject.model.entity.Employee;

public class FormEmployeeActivity extends AppCompatActivity {

    private EditText fieldName;
    private EditText fieldAge;
    private EditText fieldSalary;
    private EmployeeDao employeeDao;
    private Employee employee;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_employee);
        initializeViews();
        fillEmployee();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_form_employee_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_form_save_employee_menu){
            saveAndPutEmployee();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveAndPutEmployee() {
        Employee employeeCreated = createEmployee();
        returnEmployee(employeeCreated);


    }

    private void returnEmployee(Employee employee) {
        Intent resultInserction = new Intent();
        resultInserction.putExtra("employee", employee);
        resultInserction.putExtra("position",position);
        setResult(2, resultInserction);
    }

    private Employee createEmployee() {
        return new Employee(fieldName.getText().toString(),
                    fieldAge.getText().toString(), fieldSalary.getText().toString());
    }

    private void fillEmployee() {
        Intent employeeData = getIntent();
        if(employeeData.hasExtra("employee")){
            employee = (Employee) employeeData.getSerializableExtra("employee");
            position = employeeData.getIntExtra("position", position);
            setTitle("Edit Employee");
            setTextsEmployee();

        }else{
            setTitle("New Employee");
            employee = new Employee();
        }
    }

    private void setTextsEmployee() {
        fieldName.setText(employee.getName());
        fieldAge.setText(employee.getAge());
        fieldSalary.setText(employee.getSalary());
    }


    private void initializeViews(){
        fieldName = findViewById(R.id.activity_form_employee_name);
        fieldAge = findViewById(R.id.activity_form_employee_age);
        fieldSalary = findViewById(R.id.activity_form_employee_salary);
    }

}
