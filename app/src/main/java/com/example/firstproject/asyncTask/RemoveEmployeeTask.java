package com.example.firstproject.asyncTask;

import android.os.AsyncTask;

import com.example.firstproject.database.dao.EmployeeDaoRoom;
import com.example.firstproject.model.entity.Employee;
import com.example.firstproject.ui.recycler.adapter.ListEmployeAdapter;

public class RemoveEmployeeTask extends AsyncTask<Void, Void, Void> {

    private final ListEmployeAdapter adapter;
    private Employee employee;
    private final EmployeeDaoRoom daoRoom;

    public RemoveEmployeeTask(ListEmployeAdapter adapter, Employee employee, EmployeeDaoRoom daoRoom) {
        this.adapter = adapter;
        this.employee = employee;
        this.daoRoom = daoRoom;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        daoRoom.deleteEmployee(employee);
        //adapter.delete(employee);
        return null;
    }

}
