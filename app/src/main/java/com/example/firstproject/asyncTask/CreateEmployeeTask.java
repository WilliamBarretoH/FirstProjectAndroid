package com.example.firstproject.asyncTask;

import android.os.AsyncTask;

import com.example.firstproject.database.dao.EmployeeDaoRoom;
import com.example.firstproject.model.entity.Employee;
import com.example.firstproject.ui.recycler.adapter.ListEmployeAdapter;

public class CreateEmployeeTask extends AsyncTask<Void, Void, Void> {

    private final Employee employee;
    private final EmployeeDaoRoom daoRoom;
    private final ListEmployeAdapter adapter;

    public CreateEmployeeTask(ListEmployeAdapter adapter,Employee employee, EmployeeDaoRoom daoRoom) {
        this.adapter = adapter;
        this.employee = employee;
        this.daoRoom = daoRoom;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        daoRoom.CreateEmployee(employee);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        adapter.setEmployeeList(employee);
        super.onPostExecute(aVoid);
    }
}
