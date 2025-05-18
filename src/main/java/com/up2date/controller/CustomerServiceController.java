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
import com.up2date.service.CustomerService_ServiceLayer;
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
    private CustomerService_ServiceLayer customerService_serviceLayer;

    @PostMapping("/customer-service")
    public ResponseEntity<?> createCustomerService(@RequestBody CustomerServiceDTO customerServiceDTO){
        try{
             CustomerService savedCustomerService = customerService_serviceLayer.createCustomerService(customerServiceDTO);
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
