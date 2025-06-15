package com.up2date.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "gender")
    private String gender;

    @Column(name= "working")
    private boolean employeeWorking;

    public Employee() {
    }

    public Employee(Long employeeId, String employeeName,String gender, boolean employeeWorking) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.gender = gender;
        this.employeeWorking = employeeWorking;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isEmployeeWorking() {
        return employeeWorking;
    }

    public void setEmployeeWorking(boolean employeeWorking) {
        this.employeeWorking = employeeWorking;
    }
}
