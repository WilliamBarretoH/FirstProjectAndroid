package com.example.firstproject.api.retrofit;

import com.example.firstproject.api.retrofit.service.EmployeeService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeRetrofit {


    private final EmployeeService employeeService;

    public EmployeeRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        employeeService = retrofit.create(EmployeeService.class);
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }
}
