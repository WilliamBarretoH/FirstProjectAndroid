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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_employee);
        initializeFields();

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
            EditText name = findViewById(R.id.activity_form_employee_name);
            EditText age = findViewById(R.id.activity_form_employee_age);
            EditText salary = findViewById(R.id.activity_form_employee_salary);

            Employee employeeCreate = new Employee(name.getText().toString(),
                    age.getText().toString(), salary.getText().toString());
            new EmployeeDao().addEmployee(employeeCreate);

            Intent resultInserction = new Intent();
            resultInserction.putExtra("employeeExtra", employeeCreate);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void fillEmployee() {
        String name = fieldName.getText().toString();
        String age = fieldAge.getText().toString();
        String salary = fieldSalary.getText().toString();

        employee.setName(name);
        employee.setAge(age);
        employee.setSalary(salary);
    }


    private void initializeFields(){
        fieldName = findViewById(R.id.activity_form_employee_name);
        fieldAge = findViewById(R.id.activity_form_employee_age);
        fieldSalary = findViewById(R.id.activity_form_employee_salary);
    }

}
