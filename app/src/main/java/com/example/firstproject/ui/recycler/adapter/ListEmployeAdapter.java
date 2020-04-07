package com.example.firstproject.ui.recycler.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstproject.R;
import com.example.firstproject.model.entity.Employee;
import com.example.firstproject.ui.recycler.adapter.listener.OnItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListEmployeAdapter extends RecyclerView.Adapter<ListEmployeAdapter.EmployeeViewHolder> implements Filterable {
    private List<Employee> employees;
    private List<Employee> employeesFull;

    private final Context context;
    private OnItemClickListener onItemClickListener;

    public ListEmployeAdapter(Context context, List<Employee> employees) {
        this.context = context;
        this.employees = employees;
        this.employeesFull = employees;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ListEmployeAdapter.EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCreate = LayoutInflater.from(context)
                .inflate(R.layout.item_employee_list, parent, false);

        return new EmployeeViewHolder(viewCreate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListEmployeAdapter.EmployeeViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.vincula(employee);
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }


    public void edit(int position, Employee employee) {
        employees.set(position, employee);
        notifyItemChanged(position);

    }

    public void setEmployeeList(Employee employee) {
        employees.add(employee);
        notifyDataSetChanged();
    }

    public void updateList(List<Employee> employees) {
        this.employees.clear();
        this.employees.addAll(employees);
        this.notifyDataSetChanged();
    }

    public void delete(Employee employee) {
        employees.remove(employee);
        notifyDataSetChanged();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {


        private TextView name;

        private Employee employee;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name_employee);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(employee, getAdapterPosition());
                }
            });
        }

        public void vincula(Employee employee) {
            this.employee = employee;
            preencheCampo(employee);
        }

        private void preencheCampo(Employee employee) {
            name.setText(employee.getName());

        }

    }

    @Override
    public Filter getFilter() {
        return employeesFilter;
    }

    private Filter employeesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String charConstraint = constraint.toString();

            if(charConstraint.isEmpty()){
                employees = employeesFull;
            }else{

                List<Employee> filteredList = new ArrayList<>();

                for(Employee employee: employeesFull){
                    if(employee.getName().toLowerCase().contains(charConstraint)){
                        filteredList.add(employee);
                    }
                }
                employees = filteredList;

            }
            FilterResults results = new FilterResults();
            results.values = employees;
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            employees = (List<Employee>) results.values;
            notifyDataSetChanged();
        }
    };
}
