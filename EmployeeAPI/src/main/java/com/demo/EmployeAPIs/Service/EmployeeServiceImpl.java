package com.demo.EmployeAPIs.Service;

import com.demo.EmployeAPIs.Model.Employee;
import com.demo.EmployeAPIs.Repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmpRepo empRepo;
    @Override
    public Employee getEmployee(int id) {
       return empRepo.findById(id).orElseThrow(()->new RuntimeException("Employee not found"));
    }
    @Override
    public String addEmployee(Employee e) {
        empRepo.save(e);
        return "Employee created successfully";
    }
    @Override
    public String deleteEmployee(int id) {
        empRepo.deleteById(id);
        return "Employee deleted successfully";
    }
    @Override
    public String updateEmployee(Employee e, int id) {
       Employee e1  = empRepo.findById(id).get();
       e1.setFirstname(e.getFirstname());
       e1.setDepartment(e.getDepartment());
       e1.setLastname(e.getLastname());
       e1.setSalary(e.getSalary());
       e1.setPhoneNo(e.getPhoneNo());
       empRepo.save(e1);
       return "Employee updated successfully";
    }
    @Override
    public List<Employee> getAllEmployees() {
        return empRepo.findAll();
    }
}
