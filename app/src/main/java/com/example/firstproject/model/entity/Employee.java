package com.example.firstproject.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Employee implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private  int id = 0;

    private String name;
    private String age;
    private String salary;

    @Ignore
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
