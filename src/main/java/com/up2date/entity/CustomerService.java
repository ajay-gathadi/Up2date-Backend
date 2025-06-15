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
    private Employee employee;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "amount")
    private Double amount ;

    @Column(name = "cash_amount")
    private Double cashAmount = 0.0;

    @Column(name = "online_amount")
    private Double onlineAmount = 0.0;

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
        return employee;
    }

    public void setCSEmployeeId(Employee servicedBy) {
        this.employee = servicedBy;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCashAmount() {
        return cashAmount != null ? cashAmount : 0.0;
    }

    public void setCashAmount(Double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Double getOnlineAmount() {
        return onlineAmount != null ? onlineAmount : 0.0;
    }

    public void setOnlineAmount(Double onlineAmount) {
        this.onlineAmount = onlineAmount;
    }

    public boolean isValidSplitPayment(){
        double cash = getCashAmount();
        double online = getOnlineAmount();
        return Math.abs((cash + online) - amount) < 0.01;
    }

}
