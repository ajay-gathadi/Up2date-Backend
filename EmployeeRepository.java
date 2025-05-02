package com.up2date.repository;

import com.up2date.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmployeeName(String employeeName);
    List<Employee> findByEmployeeWorking(boolean employeeWorking);
}
