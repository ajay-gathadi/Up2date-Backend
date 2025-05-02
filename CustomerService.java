package com.up2date.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer_service")
public class CustomerService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference("customer-services")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @JsonBackReference("service-customers")
    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Column(name = "service_taken_date")
    private LocalDateTime serviceTakenDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeId;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "amount")
    private double amount;

    public CustomerService() {
    }

    public CustomerService(Customer customer, Service service, LocalDateTime serviceTakenDate) {
        this.customer = customer;
        this.service = service;
        this.serviceTakenDate = serviceTakenDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public LocalDateTime getServiceTakenDate() {
        return serviceTakenDate;
    }

    public void setServiceTakenDate(LocalDateTime serviceTakenDate) {
        this.serviceTakenDate = serviceTakenDate;
    }

    public Employee getCSEmployeeId() {
        return employeeId;
    }

    public void setCSEmployeeId(Employee servicedBy) {
        this.employeeId = employeeId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
