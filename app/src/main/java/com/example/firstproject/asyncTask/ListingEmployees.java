package com.example.firstproject.asyncTask;

import android.os.AsyncTask;

import com.example.firstproject.database.dao.EmployeeDaoRoom;
import com.example.firstproject.model.entity.Employee;

import java.util.List;

public class ListingEmployees extends AsyncTask<Void, Void, List<Employee>> {

    private final EmployeeDaoRoom dao;

    public ListingEmployees(EmployeeDaoRoom dao) {
        this.dao = dao;
    }

    @Override
    protected List<Employee> doInBackground(Void... voids) {
        return dao.ListEmployees();
    }
}
