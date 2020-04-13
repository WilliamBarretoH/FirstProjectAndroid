package com.example.firstproject.api.retrofit.service;

import com.example.firstproject.model.entity.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeService {

    @GET("employees")
    Call<List<Employee>>listAll();
}
