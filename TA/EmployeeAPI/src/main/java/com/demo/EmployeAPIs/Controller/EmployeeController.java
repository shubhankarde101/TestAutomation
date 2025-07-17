package com.demo.EmployeAPIs.Controller;

import com.demo.EmployeAPIs.Model.Employee;
import com.demo.EmployeAPIs.Service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl empService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage() throws Exception {
        return "Welcome to Home Page";
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable("id") int id) throws Exception {
        return empService.getEmployee(id);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public String addEmployee(@RequestBody Employee e1) throws Exception {

        return empService.addEmployee(e1);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public String updateEmployee(@RequestBody Employee e1, @PathVariable("id") int id) throws Exception {
        return empService.updateEmployee(e1,id);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") int id) throws Exception {
        return empService.deleteEmployee(id);
    }

    @RequestMapping(value = "/employees/all", method = RequestMethod.GET)
    public List<Employee> getAllEmployee() throws Exception {
        return empService.getAllEmployees();
    }


}

