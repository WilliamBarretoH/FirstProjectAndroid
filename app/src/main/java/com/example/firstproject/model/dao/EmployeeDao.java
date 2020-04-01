package com.example.firstproject.model.dao;

import com.example.firstproject.model.entity.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeDao {

    private static final ArrayList<Employee> employees = new ArrayList<>();

    public List<Employee> listEmployees(){
        return (List<Employee>) employees.clone();
    }

    public void addEmployee(Employee... employees){
        EmployeeDao.employees.addAll(Arrays.asList(employees));
    }
}
