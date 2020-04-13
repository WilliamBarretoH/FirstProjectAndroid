package com.example.firstproject.asyncTask;

import android.os.AsyncTask;

import com.example.firstproject.api.retrofit.EmployeeRetrofit;
import com.example.firstproject.api.retrofit.service.EmployeeService;
import com.example.firstproject.model.entity.Employee;
import com.example.firstproject.ui.recycler.adapter.ListEmployeAdapter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ApiAsyncTask extends AsyncTask<Void, Void, List<Employee>> {

    private final ListEmployeAdapter adapter;

    public ApiAsyncTask(ListEmployeAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected List<Employee> doInBackground(Void[] objects) {
        EmployeeService employeeService = new EmployeeRetrofit().getEmployeeService();
        Call<List<Employee>> listCall = employeeService.listAll();
        try {
            Response<List<Employee>> response = listCall.execute();
            List<Employee> body = response.body();
            return  body;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Employee> employees) {
        super.onPostExecute(employees);
        adapter.updateList(employees);
    }
}
