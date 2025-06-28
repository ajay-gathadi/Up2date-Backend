package com.up2date.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerServiceDTO {
    private Long customerId;
    private List<Long> serviceIds;
    private Long employeeId;
    private String paymentMethod;
    private double amount;
    private double cashAmount;
    private double onlineAmount;
    private Instant serviceTakenDate;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<Long> serviceIds) {
        this.serviceIds = serviceIds;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
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

    public Instant getServiceTakenDate() {
        return serviceTakenDate;
    }

    public void setServiceTakenDate(Instant serviceTakenDate) {
        this.serviceTakenDate = serviceTakenDate;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public double getOnlineAmount() {
        return onlineAmount;
    }

    public void setOnlineAmount(double onlineAmount) {
        this.onlineAmount = onlineAmount;
    }
}
