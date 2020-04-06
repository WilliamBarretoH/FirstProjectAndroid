package com.example.firstproject.asyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.firstproject.database.dao.EmployeeDaoRoom;
import com.example.firstproject.model.entity.Employee;
import com.example.firstproject.ui.recycler.adapter.ListEmployeAdapter;

public class UpdateEmployeeTask extends AsyncTask<Void, Void, Void> {

    private final EmployeeDaoRoom employeeDaoRoom;
    private final Employee employee;
    private final ListEmployeAdapter adapter;
    private final int position;

    public UpdateEmployeeTask(int position, EmployeeDaoRoom employeeDaoRoom, Employee employee, ListEmployeAdapter adapter) {
        this.position = position;
        this.employeeDaoRoom = employeeDaoRoom;
        this.employee = employee;
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        employeeDaoRoom.updateEmployee(employee);
        Log.i("", "Id do employee: " + employee.getId());
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.edit(position, employee);
    }
}
