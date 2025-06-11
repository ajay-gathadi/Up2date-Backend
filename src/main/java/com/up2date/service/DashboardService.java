package com.up2date.service;

import com.up2date.repository.CustomerServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {
    private final CustomerServiceRepository customerServiceRepository;

    @Autowired
    public DashboardService(CustomerServiceRepository customerServiceRepository){
        this.customerServiceRepository = customerServiceRepository;
    }

    public double calculateTotalBusinessForDate(){
        LocalDate today = LocalDate.now();
        Double total = customerServiceRepository.getTotalBusinessForDate();
        return total != null ? total : 0.0;
    }
}
