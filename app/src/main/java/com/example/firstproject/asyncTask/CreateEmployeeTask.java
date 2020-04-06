package com.example.firstproject.asyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.firstproject.database.dao.EmployeeDaoRoom;
import com.example.firstproject.model.entity.Employee;
import com.example.firstproject.ui.recycler.adapter.ListEmployeAdapter;

public class CreateEmployeeTask extends AsyncTask<Void, Void, Long> {

    private final Employee employee;
    private final EmployeeDaoRoom daoRoom;
    private final ListEmployeAdapter adapter;

    public CreateEmployeeTask(ListEmployeAdapter adapter,Employee employee, EmployeeDaoRoom daoRoom) {
        this.adapter = adapter;
        this.employee = employee;
        this.daoRoom = daoRoom;
    }

    @Override
    protected Long doInBackground(Void... voids) {
        Long employeeId = daoRoom.createEmployee(this.employee);
        Log.i("Id", "Criadoo : "+ employeeId);
        return employeeId;
    }

    @Override
    protected void onPostExecute(Long employeeId) {
        employee.setId(employeeId);
        adapter.setEmployeeList(employee);
        super.onPostExecute(null);
    }
}
