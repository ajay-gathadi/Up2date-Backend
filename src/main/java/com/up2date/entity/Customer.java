package com.up2date.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "gender")
    private String gender;

    @JsonManagedReference("customer-services")
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CustomerService> servicesTaken = new HashSet<>();

    public Customer() {}

    public Customer(String customerName, String mobileNumber, String gender, Set<CustomerService> servicesTaken) {
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.servicesTaken = servicesTaken;
    }

    public List<Service> getServices(){
        return servicesTaken.stream().map(CustomerService::getService).toList();
    }

    public List<Employee> getEmployeesWhoServed(){
        return servicesTaken.stream().map(CustomerService::getCSEmployeeId).toList();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<CustomerService> getServicesTaken() {
        return servicesTaken;
    }

    public void setServicesTaken(Set<CustomerService> servicesTaken) {
        this.servicesTaken = servicesTaken;
    }

    public void addCustomerService(CustomerService customerService) {
        this.servicesTaken.add(customerService);
        customerService.setCustomer(this);
    }
}
