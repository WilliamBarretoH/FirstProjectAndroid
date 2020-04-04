package com.example.firstproject.model.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Employee implements Serializable {

    private  int id = 0;

    private String name;
    private String age;
    private String salary;

    public Employee(String name, String age, String salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSalary() {
        return salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public boolean validId(){
        return id > 0;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
