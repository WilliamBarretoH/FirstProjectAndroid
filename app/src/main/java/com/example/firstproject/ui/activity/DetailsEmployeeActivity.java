package com.example.firstproject.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstproject.R;
import com.example.firstproject.model.dao.EmployeeDao;
import com.example.firstproject.model.entity.Employee;
import com.example.firstproject.ui.recycler.adapter.ListEmployeAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class DetailsEmployeeActivity extends AppCompatActivity {

    private TextView fieldName;
    private TextView fieldAge;
    private TextView fieldSalary;
    private Employee employee;
    private int position;
    private ListEmployeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_employee);
        fillsViews();
        setUpEditEmployeeButton();
        setUpRemoveEmployeeButton();
        fillsEmployee();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == 2 && data.hasExtra("employee") && data.hasExtra("position")) {
            employee = (Employee) data.getSerializableExtra("employee");
            position = data.getIntExtra("position", -1);
            fillsFields();
            returnEmployee();
            //finish();
        }
    }

    private void returnEmployee() {
        Intent startList = new Intent();
        startList.putExtra("employee", employee);
        startList.putExtra("position", position);
        setResult(2, startList);
    }

    private void fillsEmployee() {
        Intent employeeData = getIntent();
        if (employeeData.hasExtra("employee") && employeeData.hasExtra("position")) {
            employee = (Employee) employeeData.getSerializableExtra("employee");
            position = employeeData.getIntExtra("position", -1);

            fillsFields();
        }
    }

    private void fillsFields() {
        fieldName.setText(employee.getName());
        fieldAge.setText(employee.getAge());
        fieldSalary.setText(employee.getSalary());
    }

    private void fillsViews() {
        fieldName = findViewById(R.id.textView_details_name);
        fieldAge = findViewById(R.id.textView_details_age);
        fieldSalary = findViewById(R.id.textView_details_salary);
    }

    private void setUpEditEmployeeButton() {
        FloatingActionButton editEmployeeButton = findViewById(R.id.fab_edit_employee);
        editEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startFormEditEmployee = new Intent(DetailsEmployeeActivity.this, FormEmployeeActivity.class);

                startFormEditEmployee.putExtra("employee", employee);
                startFormEditEmployee.putExtra("position", position);
                startActivityForResult(startFormEditEmployee, 3);

            }
        });
    }

    private void setUpRemoveEmployeeButton() {
        FloatingActionButton removeEmployeeButton = findViewById(R.id.fab_delete_employee);
        removeEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyRemoveEmployee(position, employee);
                Log.i("esse e o ", "employee: " + employee.getName());


            }
        });
    }

    private void verifyRemoveEmployee(final int position, final Employee employee) {
        new AlertDialog.Builder(this)
                .setTitle("Deletando aluno")
                .setMessage("Tem certeza que deseja deletar o aluno")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DetailsEmployeeActivity.this, "Employee: " + employee.getName()
                                + "Position: " + position, Toast.LENGTH_SHORT).show();
                        new EmployeeDao().removeEmployee(position, employee);
                        adapter.delete(position);
                        finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();

    }
}