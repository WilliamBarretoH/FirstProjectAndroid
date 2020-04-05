package com.example.firstproject.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.firstproject.model.entity.Employee;

import java.util.List;

@Dao
public interface EmployeeDaoRoom {

    @Insert
    void CreateEmployee(Employee employee);

    @Query("SELECT * FROM employee")
    List<Employee> ListEmployees();
}
