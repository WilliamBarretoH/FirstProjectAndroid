package com.example.firstproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.firstproject.database.dao.EmployeeDaoRoom;
import com.example.firstproject.model.entity.Employee;

@Database(entities = {Employee.class}, version = 1, exportSchema = false)
public abstract class EmployeeDatabase extends RoomDatabase {

    public abstract EmployeeDaoRoom getEmployeeDao();

    public static EmployeeDatabase getInstance(Context context){
        return Room.databaseBuilder(context,
                EmployeeDatabase.class, "employee2.db").build();
    }
}
