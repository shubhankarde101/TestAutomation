package com.demo.EmployeAPIs.Service;

import com.demo.EmployeAPIs.Model.Employee;

import java.util.List;

public interface EmployeeService {

     Employee getEmployee(int id);
     String addEmployee(Employee e);
     String deleteEmployee(int id);
     String updateEmployee(Employee e,int id);
     List<Employee> getAllEmployees();

}
