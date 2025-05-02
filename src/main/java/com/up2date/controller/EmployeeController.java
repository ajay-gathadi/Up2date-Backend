package com.up2date.controller;

import com.up2date.entity.Employee;
import com.up2date.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/search")
    public List<Employee> getEmployeesByName(String name){
        return employeeRepository.findByEmployeeName(name);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/working")
    public List<Employee> getWorkingEmployees(){
        return employeeRepository.findByEmployeeWorking(true);
    }
}
