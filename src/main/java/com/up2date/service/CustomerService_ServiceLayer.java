package com.up2date.service;

import com.up2date.dto.CustomerServiceDTO;
import com.up2date.entity.CustomerService;

public interface CustomerService_ServiceLayer {
    CustomerService createCustomerService(CustomerServiceDTO customerServiceDTO);
}
