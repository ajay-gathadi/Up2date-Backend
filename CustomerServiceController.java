package com.up2date.controller;

import com.up2date.dto.CustomerServiceDTO;
import com.up2date.entity.Customer;
import com.up2date.entity.CustomerService;
import com.up2date.entity.Employee;
import com.up2date.entity.Service;
import com.up2date.repository.CustomerRepository;
import com.up2date.repository.CustomerServiceRepository;
import com.up2date.repository.EmployeeRepository;
import com.up2date.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CustomerServiceController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    @PostMapping("/customer-service")
    public ResponseEntity<?> createCustomerService(@RequestBody CustomerServiceDTO customerServiceDTO){
        try{
            CustomerService customerService = new CustomerService();

            Customer customer = customerRepository.findById(customerServiceDTO.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
            Service service = serviceRepository.findById(customerServiceDTO.getServiceId())
                    .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
            Employee employee = null;
            if (customerServiceDTO.getEmployeeId() != null) {
                employee = employeeRepository.findById(customerServiceDTO.getEmployeeId())
                        .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
            }

            customerService.setCustomer(customer);
            customerService.setService(service);
            customerService.setCSEmployeeId(employee);
            customerService.setServiceTakenDate(customerServiceDTO.getServiceTakenDate());
            customerService.setPaymentMethod(customerServiceDTO.getPaymentMethod());
            customerService.setAmount(customerServiceDTO.getAmount());

            CustomerService savedCustomerService = customerServiceRepository.save(customerService);
            return ResponseEntity.ok().body(Map.of(
                    "id", savedCustomerService.getId(),
                    "message", "Service added successfully"
            ));
        }catch (ResourceNotFoundException exception){
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
