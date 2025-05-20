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

@Service
class CustomerService_ServiceLayerImplementation implements CustomerService_ServiceLayer {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    @Override
    public CustomerService createCustomerService(CustomerServiceDTO customerServiceDTO){
        CustomerService customerService = new CustomerService();

        Customer customer = customerRepository.findById(customerServiceDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        com.up2date.entity.Service service = serviceRepository.findById(customerServiceDTO.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        Employee employee = null;
        if(customerServiceDTO.getEmployeeId() != null){
            employee = employeeRepository.findById(customerServiceDTO.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        }

        customerService.setCustomer(customer);
        customerService.setService(service);
        customerService.setCSEmployeeId(employee);
        customerService.setServiceTakenDate(customerServiceDTO.getServiceTakenDate());
        customerService.setPaymentMethod(customerServiceDTO.getPaymentMethod());
        customerService.setAmount(customerServiceDTO.getAmount());

        return customerServiceRepository.save(customerService);;
    }
}
