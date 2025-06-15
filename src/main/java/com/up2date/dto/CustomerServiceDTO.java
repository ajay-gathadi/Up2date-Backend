package com.up2date.dto;

import java.time.LocalDateTime;

public class CustomerServiceDTO {
    private Long customerId;
    private Long serviceId;
    private Long employeeId;
    private String paymentMethod;
    private double amount;
    private double cashAmount;
    private double onlineAmount;
    private LocalDateTime serviceTakenDate;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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

    public LocalDateTime getServiceTakenDate() {
        return serviceTakenDate;
    }

    public void setServiceTakenDate(LocalDateTime serviceTakenDate) {
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
