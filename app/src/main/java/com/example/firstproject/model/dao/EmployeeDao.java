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

    public void addEmployee(Employee employees){
        EmployeeDao.employees.addAll(Arrays.asList(employees));
    }

    public void editEmployee(int position, Employee employee){
        employees.set(position, employee);
    }

    public void removeEmployee(int position){
        employees.remove(position);
    }

    private Employee getEmployeeById(Employee employee){
        for (Employee e: employees) {
            if(e.getId() == employee.getId()) return e;
        }return null;
    }
}
