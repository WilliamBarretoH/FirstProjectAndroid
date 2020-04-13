package com.example.firstproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstproject.R;
import com.example.firstproject.api.retrofit.EmployeeRetrofit;
import com.example.firstproject.api.retrofit.service.EmployeeService;
import com.example.firstproject.asyncTask.ApiAsyncTask;
import com.example.firstproject.asyncTask.BaseAsyncTask;
import com.example.firstproject.asyncTask.CreateEmployeeTask;
import com.example.firstproject.asyncTask.ListingEmployeesTask;
import com.example.firstproject.asyncTask.UpdateEmployeeTask;
import com.example.firstproject.database.EmployeeDatabase;
import com.example.firstproject.database.dao.EmployeeDaoRoom;
import com.example.firstproject.model.dao.EmployeeDao;
import com.example.firstproject.model.entity.Employee;
import com.example.firstproject.ui.recycler.adapter.ListEmployeAdapter;
import com.example.firstproject.ui.recycler.adapter.listener.OnItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ListEmployeesActivity extends AppCompatActivity {

    private List<Employee> employees;
    private ListEmployeAdapter adapter;
    private EmployeeDao employeeDao;
    private List<Employee> allEmployees;
    private EmployeeDaoRoom employeeDaoRoom;
    private RecyclerView recyclerEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employees);
        setTitle("Employees");
        EmployeeDatabase employeeDatabase = EmployeeDatabase.getInstance(this);
        employeeDaoRoom = employeeDatabase.getEmployeeDao();

        setUpButtonNewEmployee();
        allEmployees = listingEmployees();
        setUpRecyclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //getEmployees();
        //getEmployeeAPI();
        getApiAsyncTask();
    }

    private void getApiAsyncTask(){
        new ApiAsyncTask(adapter).execute();
    }

    private void getEmployeeAPI(){
        EmployeeService employeeService = new EmployeeRetrofit().getEmployeeService();
        Call<List<Employee>> listCall = employeeService.listAll();

        new BaseAsyncTask<>(() -> {
            try {
                Response<List<Employee>> response = listCall.execute();
                List<Employee> employeesBody = response.body();
                return employeesBody;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }, employeesBody -> {
            if(employeesBody != null){
                adapter.updateList(employeesBody);
            }else{
                Toast.makeText(this, "fail api", Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }

    private void getEmployees() {
        new ListingEmployeesTask(employeeDaoRoom, adapter).execute();
    }

    private List<Employee> listingEmployees() {
        EmployeeDao employeeDao = new EmployeeDao();
        //employeeDao.addEmployee(new Employee("William","20","1500"));

        return employeeDao.listEmployees();
    }

    private void setUpRecyclerView() {

        setUpAdapter();
        setUpLayoutManager();
    }

    private void setUpLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerEmployees.setLayoutManager(layoutManager);
    }

    private void setUpAdapter() {
        recyclerEmployees = findViewById(R.id.list_employees_recyclerview);
        adapter = new ListEmployeAdapter(this, allEmployees);
        recyclerEmployees.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Employee employee, int position) {
                Intent startDetailsEmployeeActivityWithEmployeeData = new Intent(ListEmployeesActivity.this,
                        DetailsEmployeeActivity.class);
                Log.i("TAG", "onItemClick: " + employee.getId());
                startDetailsEmployeeActivityWithEmployeeData.putExtra("employee", employee);
                startDetailsEmployeeActivityWithEmployeeData.putExtra("position", position);
                startActivityForResult(startDetailsEmployeeActivityWithEmployeeData, 2);
            }

        });
    }

    private void setUpButtonNewEmployee() {
        FloatingActionButton newEmployeeButton = findViewById(R.id.fab_add_new_employee);
        newEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startFormEmployeeActivity = new Intent(ListEmployeesActivity.this,
                        FormEmployeeActivity.class);
                startActivityForResult(startFormEmployeeActivity, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //verificando o resultado do Form para criar um funcionario -employee
        if (requestCode == 1 && resultCode == 2 && data.hasExtra("employee")) {
            Employee employeeData = (Employee) data.getSerializableExtra("employee");
            if (employeeData.getName() != null) {

                new CreateEmployeeTask(adapter, employeeData, employeeDaoRoom).execute();
            } else {
                Log.i("sasa", "onActivityResult: EMPLOYEE PRECISA TER CONTEUDO");
            }

        }

        if (requestCode == 2 && resultCode == 2 && data.hasExtra("employee") && data.hasExtra("position")) {
            Employee employeeRec = (Employee) data.getSerializableExtra("employee");
            int positionRec = data.getIntExtra("position", -1);
            new UpdateEmployeeTask(positionRec, employeeDaoRoom, employeeRec, adapter).execute();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_list_employees_search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search_menu);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null) {

                    adapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }


}
