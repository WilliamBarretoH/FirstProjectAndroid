package com.example.firstproject.asyncTask;

import android.os.AsyncTask;

import com.example.firstproject.database.dao.EmployeeDaoRoom;
import com.example.firstproject.model.entity.Employee;
import com.example.firstproject.ui.recycler.adapter.ListEmployeAdapter;

import java.util.List;

public class ListingEmployeesTask extends AsyncTask<Void, Void, List<Employee>> {

    private final EmployeeDaoRoom dao;
    private final ListEmployeAdapter adapter;

    public ListingEmployeesTask(EmployeeDaoRoom dao, ListEmployeAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Employee> doInBackground(Void[] objects) {
        return dao.listEmployees();
    }

    @Override
    protected void onPostExecute(List<Employee> employees) {
        super.onPostExecute(employees);
        adapter.updateList(employees);
    }
}
