package com.up2date.service;

import com.up2date.dto.CustomerServiceDTO;
import com.up2date.entity.Customer;
import com.up2date.entity.CustomerService;
import com.up2date.entity.Employee;
import com.up2date.repository.CustomerRepository;
import com.up2date.repository.CustomerServiceRepository;
import com.up2date.repository.EmployeeRepository;
import com.up2date.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CustomerService_ServiceLayerImplementation implements CustomerService_ServiceLayer {
    private final CustomerRepository customerRepository;
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerServiceRepository customerServiceRepository;

    public CustomerService_ServiceLayerImplementation(CustomerRepository customerRepository, ServiceRepository serviceRepository,
                                                      EmployeeRepository employeeRepository, CustomerServiceRepository customerServiceRepository) {
        this.customerRepository = customerRepository;
        this.serviceRepository = serviceRepository;
        this.employeeRepository = employeeRepository;
        this.customerServiceRepository = customerServiceRepository;
    }

    @Override
    public CustomerService createCustomerService(CustomerServiceDTO customerServiceDTO){
        CustomerService customerService = new CustomerService();

        Customer customer;
        if(customerServiceDTO.getCustomerId() != null){
            customer = customerRepository.findById(customerServiceDTO.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        } else {
            customer = new Customer();
            customerRepository.save(customer);
        }

        List<com.up2date.entity.Service> services = serviceRepository.findAllById(customerServiceDTO.getServiceIds());
        if(services.size() != customerServiceDTO.getServiceIds().size()){
            throw new ResourceNotFoundException("One or more services not found");
        }

        Employee employee = null;
        if(customerServiceDTO.getEmployeeId() != null){
            employee = employeeRepository.findById(customerServiceDTO.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        }

        customerService.setCustomer(customer);
        customerService.setServices(services);
        customerService.setCSEmployeeId(employee);
        customerService.setServiceTakenDate(customerServiceDTO.getServiceTakenDate());
        customerService.setPaymentMethod(customerServiceDTO.getPaymentMethod());
        customerService.setAmount(customerServiceDTO.getAmount());
        customerService.setCashAmount(customerServiceDTO.getCashAmount());
        customerService.setOnlineAmount(customerServiceDTO.getOnlineAmount());

        if(employee != null && customerService.getAmount() != null){
            double commission = customerService.getAmount() * 0.10;
            customerService.setCommission(commission);
        }else{
            customerService.setCommission(0.0);
        }
        return customerServiceRepository.save(customerService);
    }
}
