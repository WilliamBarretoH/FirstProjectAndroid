package com.example.firstproject.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.firstproject.model.entity.Employee;

import java.util.List;

@Dao
public interface EmployeeDaoRoom {

    @Insert
    Long createEmployee(Employee employee);

    @Query("SELECT * FROM employee")
    List<Employee> listEmployees();

    @Delete
    void deleteEmployee(Employee employee);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void updateEmployee(Employee employee);

}
