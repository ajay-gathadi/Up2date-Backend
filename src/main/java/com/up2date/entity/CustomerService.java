package com.up2date.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

//    @JsonBackReference("service-customers")
//    @ManyToMany()
//    @JoinColumn(name = "service_id", nullable = false)
//    private Service service;

//    @JsonBackReference("service-customers")
    @ManyToMany()
    @JoinTable(
            name = "customer_service_junction",
            joinColumns = @JoinColumn(name = "customer_service_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> services = new ArrayList<>();

    @Column(name = "service_taken_date")
    private Instant serviceTakenDate;

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

    @Column(name = "commission")
    private Double commission;

    public CustomerService() {
    }

    public CustomerService(Customer customer, List<Service> services, Instant serviceTakenDate) {
        this.customer = customer;
        this.services = services;
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

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Instant getServiceTakenDate() {
        return serviceTakenDate;
    }

    public void setServiceTakenDate(Instant serviceTakenDate) {
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

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public boolean isValidSplitPayment(){
        double cash = getCashAmount();
        double online = getOnlineAmount();
        return Math.abs((cash + online) - amount) < 0.01;
    }

}
