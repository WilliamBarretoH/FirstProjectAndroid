package com.example.firstproject.ui.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstproject.R;
import com.example.firstproject.model.entity.Employee;
import com.example.firstproject.ui.recycler.adapter.listener.OnItemClickListener;

import java.util.List;

public class ListEmployeAdapter extends RecyclerView.Adapter <ListEmployeAdapter.EmployeeViewHolder>{

    private final List<Employee> employees;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public ListEmployeAdapter(Context context, List<Employee> employees){
        this.context = context;
        this.employees = employees;
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
        employees.set(position,employee);
        notifyDataSetChanged();
    }

    public void remove(int position){
        employees.remove(position);
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder{

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

        public void vincula(Employee employee){
            this.employee = employee;
            preencheCampo(employee);
        }

        private void preencheCampo(Employee employee) {
            name.setText(employee.getName());
        }


    }

    public void setEmployeeList(Employee employee){
        employees.add(employee);
        notifyDataSetChanged();
    }

    public void editEmployeeList(int position, Employee employee){

    }

}
