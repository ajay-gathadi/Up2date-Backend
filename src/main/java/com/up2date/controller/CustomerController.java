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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try{
//            Set<CustomerService> customerServices = new HashSet<>();
//
//            if(customer.getServicesTaken() != null && !customer.getServicesTaken().isEmpty()){
//
//                CustomerService firstService = customer.getServicesTaken().iterator().next();
//                double totalAmount = firstService.getAmount();
//                String paymentMethod = firstService.getPaymentMethod();
//                double cashAmount = firstService.getCashAmount();
//                double onlineAmount = firstService.getOnlineAmount();
//                boolean isFirstService = true;
//
//                for(CustomerService customerService : customer.getServicesTaken()){
//                    Optional<Service> optionalService = serviceRepository.findById(customerService.getService().getServiceId());
//
//                    if(optionalService.isPresent()){
//                        Service service = optionalService.get();
//                        CustomerService newCustomerService = new CustomerService();
//                        newCustomerService.setCustomer(customer);
//                        newCustomerService.setService(service);
//                        newCustomerService.setServiceTakenDate(LocalDateTime.now());
//
//                        if(isFirstService){
//                            newCustomerService.setAmount(totalAmount);
//                            newCustomerService.setPaymentMethod(paymentMethod);
//                            newCustomerService.setCashAmount(cashAmount);
//                            newCustomerService.setOnlineAmount(onlineAmount);
//                            isFirstService = false;
//                        } else {
//                            newCustomerService.setAmount(0.0);
//                            newCustomerService.setPaymentMethod(paymentMethod);
//                            newCustomerService.setCashAmount(0.0);
//                            newCustomerService.setOnlineAmount(0.0);
//                        }
//
//                        if(customerService.getCSEmployeeId() != null){
//                            newCustomerService.setCSEmployeeId(customerService.getCSEmployeeId());
//                        }
//                        customerServices.add(newCustomerService);
//                    }
//                    /*
//                    Service service = customerService.getService();
//                    if(service != null && service.getServiceId() != null){
//                        serviceRepository.findById(service.getServiceId()).ifPresent(validServices -> {
//                            CustomerService newCustomerService = new CustomerService();
//                            newCustomerService.setCustomer(customer);
//                            newCustomerService.setService(validServices);
//                            newCustomerService.setServiceTakenDate(LocalDateTime.now());
//                            newCustomerService.setAmount(customerService.getAmount());
//                            newCustomerService.setPaymentMethod(customerService.getPaymentMethod());
//                            newCustomerService.setCashAmount(customerService.getCashAmount());
//                            newCustomerService.setOnlineAmount(customerService.getOnlineAmount());
//
//                            if(customerService.getCSEmployeeId() != null){
//                                newCustomerService.setCSEmployeeId(customerService.getCSEmployeeId());
//                            }
//                            customerServices.add(newCustomerService);
//                        });
//                    }
//                    */
//                }
//            }
//            customer.setServicesTaken(customerServices);
//            Customer savedCustomer = customerRepository.save(customer);
//            System.out.println("Received Customer is : " + customer);
            Customer savedCustomer = customerRepository.save(customer);
            return ResponseEntity.ok(savedCustomer);
        }catch (Exception e){
            System.err.println("Error saving customer: " + e.getMessage());
            throw e;
        }
    }

    @GetMapping
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("search")
    public ResponseEntity<?> searchCustomerByName(@RequestParam String name){
        List<Customer> customerList = customerRepository.findByCustomerNameContainingIgnoreCase(name);
        if (customerList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customerList);
    }
}
