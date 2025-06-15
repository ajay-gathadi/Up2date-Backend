package com.up2date.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "services")
public class Service{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "for_male")
    private boolean forMale;

    @Column(name = "for_female")
    private boolean forFemale;

    @JsonManagedReference("service-customers")
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private Set<CustomerService> customerServices = new HashSet<>();

    public Service() {
    }

    //Would be useful to add new services without specifying the ID(Automatically generated)
    public Service(String serviceName, boolean forMale, boolean forFemale){
        this.serviceName = serviceName;
        this.forMale = forMale;
        this.forFemale = forFemale;
    }

    public Service(Long serviceId, String serviceName, boolean forMale, boolean forFemale, Set<CustomerService> customerServices) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.forMale = forMale;
        this.forFemale = forFemale;
        this.customerServices = customerServices;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isForMale() {
        return forMale;
    }

    public void setForMale(boolean forMale) {
        this.forMale = forMale;
    }

    public boolean isForFemale() {
        return forFemale;
    }

    public void setForFemale(boolean forFemale) {
        this.forFemale = forFemale;
    }

    public Set<CustomerService> getCustomerServices() {
        return customerServices;
    }

    public void setCustomerServices(Set<CustomerService> customerServices) {
        this.customerServices = customerServices;
    }

    public void addCustomerService(CustomerService customerService) {
        this.customerServices.add(customerService);
        customerService.setService(this);
    }
}
