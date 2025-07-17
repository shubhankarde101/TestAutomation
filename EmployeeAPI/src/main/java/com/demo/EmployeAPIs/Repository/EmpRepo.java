package com.demo.EmployeAPIs.Repository;

import com.demo.EmployeAPIs.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer> {



}
